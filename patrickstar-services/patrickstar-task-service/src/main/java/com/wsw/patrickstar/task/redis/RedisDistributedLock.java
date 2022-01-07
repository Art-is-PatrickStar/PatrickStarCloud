package com.wsw.patrickstar.task.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author WangSongWen
 * @Date 2021/7/25 23:34
 * @Description: redis分布式锁
 */
@Slf4j
@Component
public class RedisDistributedLock {
    @Resource
    private RedissonClient redissonClient;

    private static final RedisLock FAILURE_LOCK = new RedisLock();

    /**
     * 尝试获取锁，可防止死锁
     *
     * @param objectName 锁对象
     * @param waitTime   获取锁最多等待时间
     * @param leaseTime  持有锁时间
     * @param unit       时间单位
     * @return
     */
    public RedisLock tryLock(String objectName, long waitTime, long leaseTime, TimeUnit unit) {
        RLock rLock = redissonClient.getLock(objectName);
        boolean lockSuccessed;
        try {
            lockSuccessed = rLock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return FAILURE_LOCK;
        }
        if (lockSuccessed) {
            return new RedisLock(rLock, true);
        }
        return FAILURE_LOCK;
    }
}
