package com.wsw.patrickstar.task.service.impl;

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
import com.wsw.patrickstar.common.utils.DateUtils;
import com.wsw.patrickstar.common.utils.SnowflakeUtils;
import com.wsw.patrickstar.task.entity.TaskEntity;
import com.wsw.patrickstar.task.mapper.TaskMapper;
import com.wsw.patrickstar.task.mapstruct.ITaskConvert;
import com.wsw.patrickstar.task.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

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
@CacheConfig(cacheNames = "task", cacheManager = "taskCacheManager")
public class TaskServiceImpl extends ServiceImpl<TaskMapper, TaskEntity> implements TaskService {
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private RecepienterCloudService recepienterCloudService;
    @Value("${snowf.workId}")
    private long workId;

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
    @CachePut(key = "#taskDTO.taskId", unless = "#result == null")
    public void updateTask(TaskDTO taskDTO) {
        TaskEntity taskEntity = ITaskConvert.INSTANCE.dtoToEntity(taskDTO);
        this.updateById(taskEntity);
            /*UpdateWrapper<TaskEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("task_id", taskDTO.getTaskId());
            this.update(taskEntity, updateWrapper);*/
    }

    @Override
    @CacheEvict(key = "#p0", allEntries = false)
    public void deleteTask(Long taskId) {
        this.baseMapper.deleteById(taskId);
            /*QueryWrapper<TaskEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("task_id", taskId);
            this.baseMapper.delete(queryWrapper);*/
    }

    @Override
    public PageInfo<TaskDTO> selectTask(TaskRequestDTO taskRequestDTO) {
        Page<?> page = new Page<>(taskRequestDTO.getStart(), taskRequestDTO.getLength());
        if (StringUtils.isNotBlank(taskRequestDTO.getCreateTimeStart()) && StringUtils.isBlank(taskRequestDTO.getCreateTimeEnd())) {
            taskRequestDTO.setCreateTimeEnd(DateUtils.dateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS_SSS, new Date()));
        }
        if (StringUtils.isNotBlank(taskRequestDTO.getUpdateTimeStart()) && StringUtils.isBlank(taskRequestDTO.getUpdateTimeEnd())) {
            taskRequestDTO.setUpdateTimeEnd(DateUtils.dateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS_SSS, new Date()));
        }
        IPage<TaskEntity> taskEntityIPage = taskMapper.selectTask(page, taskRequestDTO);
        return PageInfo.fromIPage(taskEntityIPage.convert(ITaskConvert.INSTANCE::entityToDto));
    }

    @Override
    @Cacheable(key = "#p0", unless = "#result == null")
    public TaskDTO selectTaskByTaskId(Long taskId) {
        TaskEntity taskEntity = this.lambdaQuery().eq(TaskEntity::getTaskId, taskId).one();
        return ITaskConvert.INSTANCE.entityToDto(taskEntity);
    }
}
