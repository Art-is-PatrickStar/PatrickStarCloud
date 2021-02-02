package com.wsw.patrickstar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wsw.patrickstar.api.OperationType;
import com.wsw.patrickstar.entity.Task;
import com.wsw.patrickstar.feign.client.RecepienterClient;
import com.wsw.patrickstar.mapper.TaskMapper;
import com.wsw.patrickstar.message.AsyncSendMessage;
import com.wsw.patrickstar.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
@Service
@Slf4j
@CacheConfig(cacheNames = "task", cacheManager = "taskCacheManager")
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private RecepienterClient recepienterClient;
    @Resource
    private AsyncSendMessage asyncSendMessage;
    @Resource
    private RedissonClient redissonClient;

    private static final String REDIS_LOCK_KEY = "task-service";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Task createTask(Task task) {
        // 添加任务
        taskMapper.insert(task);
        // 同步调用
        // 调用recepienter服务添加领取人员信息
        recepienterClient.create(task.getTaskId(), task.getTaskName(), task.getRecepientName(), new Date().toString());
        // 发送消息到数据同步服务
        // 防止消息重复发送 Redis分布式锁
        RLock lock = redissonClient.getLock(REDIS_LOCK_KEY);
        lock.lock(30, TimeUnit.SECONDS);
        try {
            // RabbitMQ异步发消息
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("operationType", OperationType.ADD.getOperation());
            messageMap.put("taskId", task.getTaskId());
            asyncSendMessage.asyncSendMessage(messageMap);
            log.info("新增数据---发送消息到数据同步服务---成功! taskId = " + task.getTaskId());
        } catch (Exception e) {
            log.error("新增数据---发送消息到数据同步服务---失败! taskId =  " + task.getTaskId() + " errorMessage: " + e.getMessage());
        } finally {
            lock.unlock();
        }
        return task;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(key = "#task.taskId")
    public Task updateTaskById(Task task) {
        taskMapper.updateById(task);
        RLock lock = redissonClient.getLock(REDIS_LOCK_KEY);
        lock.lock(30, TimeUnit.SECONDS);
        try {
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("operationType", OperationType.UPDATE.getOperation());
            messageMap.put("taskId", task.getTaskId());
            asyncSendMessage.asyncSendMessage(messageMap);
            log.info("更新数据---发送消息到数据同步服务---成功! taskId = " + task.getTaskId());
        } catch (Exception e) {
            log.error("更新数据---发送消息到数据同步服务---失败! taskId =  " + task.getTaskId() + " errorMessage: " + e.getMessage());
        } finally {
            lock.unlock();
        }
        return task;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(key = "#task.taskId")
    public Task updateTaskByName(Task task) {
        UpdateWrapper<Task> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("task_name", task.getTaskName());
        taskMapper.update(task, updateWrapper);
        RLock lock = redissonClient.getLock(REDIS_LOCK_KEY);
        lock.lock(30, TimeUnit.SECONDS);
        try {
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("operationType", OperationType.UPDATE.getOperation());
            messageMap.put("taskId", task.getTaskId());
            asyncSendMessage.asyncSendMessage(messageMap);
            log.info("更新数据---发送消息到数据同步服务---成功! taskId = " + task.getTaskId());
        } catch (Exception e) {
            log.error("更新数据---发送消息到数据同步服务---失败! taskId =  " + task.getTaskId() + " errorMessage: " + e.getMessage());
        } finally {
            lock.unlock();
        }
        return task;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(key = "#task.taskId")
    public Task updateTaskStatusByTaskId(Task task) {
        UpdateWrapper<Task> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .set("task_status", task.getTaskStatus())
                .eq("task_id", task.getTaskId());
        taskMapper.update(task, updateWrapper);
        RLock lock = redissonClient.getLock(REDIS_LOCK_KEY);
        lock.lock(30, TimeUnit.SECONDS);
        try {
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("operationType", OperationType.UPDATE.getOperation());
            messageMap.put("taskId", task.getTaskId());
            asyncSendMessage.asyncSendMessage(messageMap);
            log.info("更新数据---发送消息到数据同步服务---成功! taskId = " + task.getTaskId());
        } catch (Exception e) {
            log.error("更新数据---发送消息到数据同步服务---失败! taskId =  " + task.getTaskId() + " errorMessage: " + e.getMessage());
        } finally {
            lock.unlock();
        }
        return task;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#p0", allEntries = false)
    public int deleteTaskByTaskId(Long taskId) {
        int result;
        result = taskMapper.deleteById(taskId);
        RLock lock = redissonClient.getLock(REDIS_LOCK_KEY);
        lock.lock(30, TimeUnit.SECONDS);
        try {
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("operationType", OperationType.DELETE.getOperation());
            messageMap.put("taskId", taskId);
            asyncSendMessage.asyncSendMessage(messageMap);
            log.info("删除数据---发送消息到数据同步服务---成功! taskId = " + taskId);
        } catch (Exception e) {
            log.error("删除数据---发送消息到数据同步服务---失败! taskId =  " + taskId + " errorMessage: " + e.getMessage());
        } finally {
            lock.unlock();
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#p0", allEntries = false)
    public int deleteTaskByTaskName(String taskName) {
        int result;
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_name", taskName);
        result = taskMapper.delete(queryWrapper);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(key = "#p0")
    public Task selectTaskById(Long taskId) {
        return taskMapper.selectById(taskId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Task selectEsTaskById(Long taskId) {
        return taskMapper.selectById(taskId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(key = "#p0")
    public List<Task> selectTaskByName(String taskName) {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_name", taskName);
        return taskMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(key = "#p0")
    public List<Task> selectTaskByStatus(char taskStatus) {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_status", taskStatus);
        return taskMapper.selectList(queryWrapper);
    }
}
