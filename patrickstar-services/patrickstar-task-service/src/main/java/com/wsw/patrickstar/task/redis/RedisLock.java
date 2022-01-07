package com.wsw.patrickstar.task.redis;

import org.redisson.api.RLock;

/**
 * @Author WangSongWen
 * @Date 2021/7/25 23:34
 * @Description: 获取redis锁的封装对象
 */
public class RedisLock {
    private RLock rLock;
    private boolean lockSuccessed = false;

    public RedisLock() {
    }

    public RedisLock(RLock rLock, boolean lockSuccessed) {
        this.rLock = rLock;
        this.lockSuccessed = lockSuccessed;
    }

    public void unlock() {
        // 如果锁被当前线程持有，则释放
        if (rLock != null && rLock.isHeldByCurrentThread()) {
            rLock.unlock();
        }
    }

    public boolean isLockSuccessed() {
        return lockSuccessed;
    }

}
