package com.wsw.patrickstar.util;

import org.redisson.api.RLock;

/**
 * @Author WangSongWen
 * @Date 2021/7/25 23:34
 * @Description: redis锁
 */
public class RedisLock {
    private RLock rLock;
    private boolean lockSuccessed;

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

    public void setLockSuccessed(boolean lockSuccessed) {
        this.lockSuccessed = lockSuccessed;
    }

}
