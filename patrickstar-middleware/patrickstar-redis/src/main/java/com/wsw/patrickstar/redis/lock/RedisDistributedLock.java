package com.wsw.patrickstar.redis.lock;

import com.wsw.patrickstar.common.exception.LockAcquireFailException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description: redis分布式锁
 * @Author: wangsongwen
 * @Date: 2022/11/14 00:53
 */
@Slf4j
@Component
public class RedisDistributedLock {
    @Resource
    private RedissonClient redissonClient;

    public RedisDistributedLock(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * @param lockName  锁名
     * @param waitTime  获取锁的最大等待时间
     * @param leaseTime 获取到锁后最大持有时间
     * @param timeUnit  时间单位
     * @param action    锁期间执行的回调方法
     * @return T        回调方法返回值
     */
    public <T> T tryLock(String lockName, long waitTime, long leaseTime, TimeUnit timeUnit, LockCallBack<T> action) {
        RLock lock = redissonClient.getLock(lockName);
        try {
            if (!lock.tryLock(waitTime, leaseTime, timeUnit)) {
                throw new LockAcquireFailException(String.format("获取分布式锁失败!锁名:%s", lock.getName()));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        try {
            return action.doInLock();
        } finally {
            lock.unlock();
        }
    }
}
