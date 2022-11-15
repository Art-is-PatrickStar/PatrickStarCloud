package com.wsw.patrickstar.redis.lock;

import org.springframework.lang.Nullable;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/15 10:08
 */
public interface LockCallBack<T> {
    /**
     * 锁期间执行的回调方法
     *
     * @return T
     */
    @Nullable
    T doInLock();
}