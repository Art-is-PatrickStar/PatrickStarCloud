package com.wsw.patrickstar.task.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsw.patrickstar.api.model.dto.TaskDTO;
import com.wsw.patrickstar.api.model.dto.TaskRecordDTO;
import com.wsw.patrickstar.api.model.dto.TaskRequestDTO;
import com.wsw.patrickstar.api.response.Result;
import com.wsw.patrickstar.api.response.ResultStatusEnums;
import com.wsw.patrickstar.api.service.RecepienterCloudService;
import com.wsw.patrickstar.common.base.PageInfo;
import com.wsw.patrickstar.common.enums.TaskStatusEnum;
import com.wsw.patrickstar.common.enums.TaskTypeEnum;
import com.wsw.patrickstar.common.exception.BusinessException;
import com.wsw.patrickstar.common.exception.LockAcquireFailException;
import com.wsw.patrickstar.common.utils.SnowflakeUtils;
import com.wsw.patrickstar.redis.lock.RedisDistributedLock;
import com.wsw.patrickstar.task.entity.TaskEntity;
import com.wsw.patrickstar.task.mapper.TaskMapper;
import com.wsw.patrickstar.task.mapstruct.ITaskConvert;
import com.wsw.patrickstar.task.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @Author WangSongWen
 * @Date: Created in 14:28 2020/11/9
 * @Description: task主服务
 * <p>
 * redis缓存:
 * 1.Cacheable: 将查询结果缓存到redis中,(key="#p0")指定传入的第一个参数作为redis的key
 * 2.CachePut: 指定key,将更新的结果同步到redis中
 * 3.CacheEvict: 指定key,删除缓存数据,(allEntries=true)方法调用后将立即清空所有缓存
 * <p>
 * 同步调用: openFeign
 * 异步调用: RabbitMQ
 */
@Slf4j
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, TaskEntity> implements TaskService {
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private RecepienterCloudService recepienterCloudService;
    @Resource
    private RedisDistributedLock redisDistributedLock;
    @Value("${snowf.workId}")
    private long workId;
    //分页大小
    private static final int PAGE_SIZE = 200;

    @Override
    @Transactional(rollbackFor = {BusinessException.class, Exception.class})
    public void createTask(TaskDTO taskDTO) {
        //赋值任务id
        taskDTO.setTaskId(SnowflakeUtils.genId(workId));
        TaskEntity taskEntity = ITaskConvert.INSTANCE.dtoToEntity(taskDTO);
        taskEntity.setCreateTime(new Date());
        taskEntity.setUpdateTime(new Date());
        // 添加任务
        this.save(taskEntity);
        // 同步调用
        // 调用recepienter服务添加任务记录
        TaskRecordDTO taskRecordDTO = TaskRecordDTO.builder()
                .taskId(taskEntity.getTaskId())
                .taskType(TaskTypeEnum.PRODUCT.getCode())
                .taskStatus(TaskStatusEnum.TODO.getCode())
                .createUser(taskEntity.getCreateUser())
                .updateUser(taskEntity.getUpdateUser())
                .build();
        Result<Void> result = recepienterCloudService.createTaskRecord(taskRecordDTO);
        if (!result.getSuccess()) {
            throw new BusinessException(ResultStatusEnums.MICRO_SERVICE_EXCEPTION);
        }
    }

    @Override
    public void updateTask(TaskDTO taskDTO) {
        TaskEntity taskEntity = ITaskConvert.INSTANCE.dtoToEntity(taskDTO);
        this.updateById(taskEntity);
            /*UpdateWrapper<TaskEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("task_id", taskDTO.getTaskId());
            this.update(taskEntity, updateWrapper);*/
    }

    @Override
    public void deleteTask(Long taskId) {
        this.baseMapper.deleteById(taskId);
            /*QueryWrapper<TaskEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("task_id", taskId);
            this.baseMapper.delete(queryWrapper);*/
    }

    @Override
    public PageInfo<TaskDTO> selectTask(TaskRequestDTO taskRequestDTO) {
        Page<?> page = new Page<>(taskRequestDTO.getStart(), taskRequestDTO.getLength());
        if (!Objects.isNull(taskRequestDTO.getCreateTimeStart()) && Objects.isNull(taskRequestDTO.getCreateTimeEnd())) {
            taskRequestDTO.setCreateTimeEnd(new Date());
        }
        if (!Objects.isNull(taskRequestDTO.getUpdateTimeStart()) && Objects.isNull(taskRequestDTO.getUpdateTimeEnd())) {
            taskRequestDTO.setUpdateTimeEnd(new Date());
        }
        IPage<TaskEntity> taskEntityIPage = taskMapper.selectTask(page, taskRequestDTO);
        return PageInfo.fromIPage(taskEntityIPage.convert(ITaskConvert.INSTANCE::entityToDto));
    }

    @Override
    public TaskDTO selectTaskByTaskId(Long taskId) {
        TaskEntity taskEntity = this.lambdaQuery().eq(TaskEntity::getTaskId, taskId).one();
        return ITaskConvert.INSTANCE.entityToDto(taskEntity);
    }

    @Override
    public void hisResourceProcess(TaskRequestDTO taskRequestDTO) {
        //考虑用户点击频繁，设置分布式锁
        try {
            redisDistributedLock.tryLock(taskRequestDTO.getTaskId().toString(), 1, 1, TimeUnit.SECONDS, () -> true);
        } catch (LockAcquireFailException e) {
            throw new BusinessException(ResultStatusEnums.CLICK_FREQUENT);
        }
        //异步执行(使用默认内置线程池ForkJoinPool.commonPool(), 也可自定义线程池)
        CompletableFuture.runAsync(() -> {
            try {
                redisDistributedLock.tryLock(taskRequestDTO.getTaskId().toString(), 1, 20, TimeUnit.SECONDS, () -> {
                    List<TaskEntity> taskEntities = this.lambdaQuery()
                            .between(TaskEntity::getUpdateTime, taskRequestDTO.getUpdateTimeStart(), taskRequestDTO.getUpdateTimeEnd())
                            .list();
                    try {
                        Thread.sleep(40 * 1000);
                        //处理
                        log.info(JSON.toJSONString(taskEntities));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                });
            } catch (Exception e) {
                throw new BusinessException(ResultStatusEnums.SYSTEM_EXCEPTION);
            }
        });
    }

    @Override
    public void taskBatchProcess(TaskRequestDTO taskRequestDTO) {
        //获取总数
        Integer count = this.lambdaQuery()
                .between(TaskEntity::getUpdateTime, taskRequestDTO.getUpdateTimeStart(), taskRequestDTO.getUpdateTimeEnd())
                .count();
        //获取页数
        int pageCount = count % PAGE_SIZE == 0 ? count / PAGE_SIZE : (count / PAGE_SIZE) + 1;
        log.info("taskBatchProcess方法执行, count: {}, pageCount: {}.", count, pageCount);
        //任务列表
        List<Callable<List<TaskEntity>>> tasks = new ArrayList<>();
        //固定线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //加入任务列表
        for (int i = 1; i <= pageCount; i++) {
            tasks.add(new ThreadQuery(i, taskRequestDTO.getUpdateTimeStart(), taskRequestDTO.getUpdateTimeEnd(), this.baseMapper));
        }
        try {
            //多线程执行
            List<Future<List<TaskEntity>>> futures = executorService.invokeAll(tasks);
            if (CollectionUtil.isNotEmpty(futures)) {
                for (Future<List<TaskEntity>> future : futures) {
                    List<TaskEntity> taskEntities = future.get();
                    if (CollectionUtil.isNotEmpty(taskEntities)) {
                        executorService.execute(() -> {
                            //处理
                            System.out.println(taskEntities);
                        });
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("多线程future执行出错: " + e);
        } catch (ExecutionException e) {
            throw new RuntimeException("多线程future.get()执行出错: " + e);
        } finally {
            executorService.shutdown();
        }
    }

    //分页查询数据内部类
    public static class ThreadQuery implements Callable<List<TaskEntity>> {
        private final int pageIndex;
        private final Date beginUpdateTime;
        private final Date endUpdateTime;
        private final TaskMapper taskMapper;

        public ThreadQuery(int pageIndex, Date beginUpdateTime, Date endUpdateTime, TaskMapper taskMapper) {
            this.pageIndex = pageIndex;
            this.beginUpdateTime = beginUpdateTime;
            this.endUpdateTime = endUpdateTime;
            this.taskMapper = taskMapper;
        }

        @Override
        public List<TaskEntity> call() throws Exception {
            try {
                Page<TaskEntity> page = new Page<>(pageIndex, PAGE_SIZE);
                TaskRequestDTO requestDTO = new TaskRequestDTO();
                requestDTO.setUpdateTimeStart(beginUpdateTime);
                requestDTO.setUpdateTimeEnd(endUpdateTime);
                IPage<TaskEntity> iPage = taskMapper.selectTask(page, requestDTO);
                return iPage.getRecords();
            } catch (Exception e) {
                throw new Exception("分页查询数据异常: " + e);
            }
        }
    }
}
