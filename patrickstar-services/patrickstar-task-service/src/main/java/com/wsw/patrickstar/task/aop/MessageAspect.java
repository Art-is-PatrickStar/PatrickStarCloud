//package com.wsw.patrickstar.task.aop;
//
//import com.alibaba.fastjson.JSONObject;
//import com.wsw.patrickstar.common.exception.CloudServiceException;
//import com.wsw.patrickstar.common.enums.OperationType;
//import com.wsw.patrickstar.task.entity.TaskEntity;
//import com.wsw.patrickstar.task.message.AsyncSendMessage;
//import com.wsw.patrickstar.task.redis.RedisLock;
//import com.wsw.patrickstar.task.service.RedisService;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author WangSongWen
// * @Date: Created in 16:50 2021/2/2
// * @Description: 发送数据同步消息切面
// */
//@Slf4j
//@Aspect
//@Order(1) // 越小越先执行
//@Component
//public class MessageAspect {
//    @Resource
//    private RedisService redisService;
//    @Resource
//    private AsyncSendMessage asyncSendMessage;
//
//    private static final String REDIS_LOCK_KEY = "send-message-lock";
//
//    @Pointcut("execution(* com.wsw.patrickstar.task.service.impl.TaskServiceImpl.createTask(..))")
//    public void addPointCutService() {
//    }
//
//    @Pointcut("execution(* com.wsw.patrickstar.task.service.impl.TaskServiceImpl.updateTask*(..))")
//    public void updatePointCutService() {
//    }
//
//    @Pointcut("execution(* com.wsw.patrickstar.task.service.impl.TaskServiceImpl.deleteTaskByTaskId(..))")
//    public void deletePointCutService() {
//    }
//
//    @Around("addPointCutService()")
//    public Object sendAddMessage(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object result;
//        Object[] args = joinPoint.getArgs();
//        TaskEntity task = (TaskEntity) args[0];
//        // 发送消息到数据同步服务
//        // 防止消息重复发送 Redis分布式锁
//        RedisLock redisLock = redisService.tryLock(REDIS_LOCK_KEY + ":sendAddMessage");
//        try {
//            if (!redisLock.isLockSuccessed()) {
//                log.error("redis分布式锁获取失败!");
//                return null;
//            }
//            result = joinPoint.proceed(args);
//            // RabbitMQ异步发消息
//            Map<String, Object> messageMap = new HashMap<>();
//            messageMap.put("operationType", OperationType.ADD.getOperation());
//            messageMap.put("taskId", task.getTaskId());
//            asyncSendMessage.asyncSendMessage(JSONObject.toJSONString(messageMap));
//            log.info("新增数据---发送消息到数据同步服务---成功! taskId = " + task.getTaskId());
//        } catch (Exception e) {
//            redisLock.unlock();
//            log.error("新增数据---发送消息到数据同步服务---失败! taskId =  " + task.getTaskId() + " errorMessage: " + e.getMessage());
//            throw new CloudServiceException(e.getMessage(), e.getCause());
//        } finally {
//            redisLock.unlock();
//        }
//        return result;
//    }
//
//    @Around("updatePointCutService()")
//    public Object sendUpdateMessage(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object result;
//        Object[] args = joinPoint.getArgs();
//        TaskEntity task = (TaskEntity) args[0];
//        RedisLock redisLock = redisService.tryLock(REDIS_LOCK_KEY + ":sendUpdateMessage");
//        try {
//            if (!redisLock.isLockSuccessed()) {
//                log.error("redis分布式锁获取失败!");
//                return null;
//            }
//            result = joinPoint.proceed(args);
//            Map<String, Object> messageMap = new HashMap<>();
//            messageMap.put("operationType", OperationType.UPDATE.getOperation());
//            messageMap.put("taskId", task.getTaskId());
//            asyncSendMessage.asyncSendMessage(JSONObject.toJSONString(messageMap));
//            log.info("更新数据---发送消息到数据同步服务---成功! taskId = " + task.getTaskId());
//        } catch (Exception e) {
//            redisLock.unlock();
//            log.error("更新数据---发送消息到数据同步服务---失败! taskId =  " + task.getTaskId() + " errorMessage: " + e.getMessage());
//            throw new CloudServiceException(e.getMessage(), e.getCause());
//        } finally {
//            redisLock.unlock();
//        }
//        return result;
//    }
//
//    @Around("deletePointCutService()")
//    public Object sendDeleteMessage(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object result;
//        Object[] args = joinPoint.getArgs();
//        Long taskId = (Long) args[0];
//        RedisLock redisLock = redisService.tryLock(REDIS_LOCK_KEY + ":sendDeleteMessage");
//        try {
//            if (!redisLock.isLockSuccessed()) {
//                log.error("redis分布式锁获取失败!");
//                return null;
//            }
//            result = joinPoint.proceed(args);
//            Map<String, Object> messageMap = new HashMap<>();
//            messageMap.put("operationType", OperationType.DELETE.getOperation());
//            messageMap.put("taskId", taskId);
//            asyncSendMessage.asyncSendMessage(JSONObject.toJSONString(messageMap));
//            log.info("删除数据---发送消息到数据同步服务---成功! taskId = " + taskId);
//        } catch (Exception e) {
//            redisLock.unlock();
//            log.error("删除数据---发送消息到数据同步服务---失败! taskId =  " + taskId + " errorMessage: " + e.getMessage());
//            throw new CloudServiceException(e.getMessage(), e.getCause());
//        } finally {
//            redisLock.unlock();
//        }
//        return result;
//    }
//}
