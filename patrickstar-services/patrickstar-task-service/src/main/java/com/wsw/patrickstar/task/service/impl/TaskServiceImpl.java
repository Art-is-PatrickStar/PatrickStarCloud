package com.wsw.patrickstar.task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wsw.patrickstar.api.domain.Recepienter;
import com.wsw.patrickstar.api.domain.Task;
import com.wsw.patrickstar.api.service.RecepienterCloudService;
import com.wsw.patrickstar.common.exception.CloudServiceException;
import com.wsw.patrickstar.task.mapper.TaskMapper;
import com.wsw.patrickstar.task.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private RecepienterCloudService recepienterCloudService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createTask(Task task) throws CloudServiceException {
        int result;
        // 添加任务
        result = taskMapper.insert(task);
        // 同步调用
        // 调用recepienter服务添加领取人员信息
        Recepienter recepienter = Recepienter.builder().taskId(task.getTaskId()).taskName(task.getTaskName())
                .name(task.getRecepientName()).remark(new Date().toString()).build();
        result = recepienterCloudService.create(recepienter);
        return result;
    }

    @Override
    @CachePut(key = "#task.taskId", unless = "#result == null")
    public int updateTaskById(Task task) throws CloudServiceException {
        return taskMapper.updateById(task);
    }

    @Override
    @CachePut(key = "#task.taskId", unless = "#result == null")
    public int updateTaskByName(Task task) throws CloudServiceException {
        int result;
        UpdateWrapper<Task> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("task_name", task.getTaskName());
        result = taskMapper.update(task, updateWrapper);
        return result;
    }

    @Override
    @CachePut(key = "#task.taskId", unless = "#result == null")
    public int updateTaskStatusByTaskId(Task task) throws CloudServiceException {
        int result;
        UpdateWrapper<Task> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("task_status", task.getTaskStatus())
                .eq("task_id", task.getTaskId());
        result = taskMapper.update(task, updateWrapper);
        return result;
    }

    @Override
    @CacheEvict(key = "#p0", allEntries = false)
    public int deleteTaskByTaskId(Long taskId) throws CloudServiceException {
        return taskMapper.deleteById(taskId);
    }

    // 这个方法没有实现数据同步！
    @Override
    @CacheEvict(key = "#p0", allEntries = false)
    public int deleteTaskByTaskName(String taskName) throws CloudServiceException {
        int result;
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_name", taskName);
        result = taskMapper.delete(queryWrapper);
        return result;
    }

    @Override
    @Cacheable(key = "#p0", unless = "#result == null")
    public Task selectTaskById(Long taskId) throws CloudServiceException {
        return taskMapper.selectById(taskId);
    }

    @Override
    @Cacheable(key = "#p0", unless = "#result == null")
    public List<Task> selectTaskByName(String taskName) throws CloudServiceException {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_name", taskName);
        return taskMapper.selectList(queryWrapper);
    }

    @Override
    @Cacheable(key = "#p0", unless = "#result == null")
    public List<Task> selectTaskByStatus(char taskStatus) throws CloudServiceException {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_status", taskStatus);
        return taskMapper.selectList(queryWrapper);
    }
}
