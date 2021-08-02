package com.wsw.patrickstar.service;

import com.wsw.patrickstar.util.RedisLock;

import java.util.concurrent.TimeUnit;

/**
 * @Author WangSongWen
 * @Date 2021/7/25 22:52
 * @Description: redis服务接口
 */
public interface RedisService {
    /**
     * 获取分布式锁接口
     *
     * @param lockKey
     * @return
     */
    RedisLock tryLock(String lockKey);

    /**
     * 存储refreshToken
     *
     * @param hashName
     * @param hashKey
     * @param hashValue
     */
    void setRefreshToken(String hashName, String hashKey, String hashValue);

    /**
     * 获取refreshToken
     *
     * @param hashName
     * @param hashKey
     * @return
     */
    String getRefreshToken(String hashName, String hashKey);

    /**
     * 设置refreshToken过期时间
     *
     * @param hashName
     * @param timeOut
     * @param unit
     */
    void setRefreshTokenExpire(String hashName, long timeOut, TimeUnit unit);

    /**
     * 存储old token
     *
     * @param key
     * @param value
     * @param timeOut
     * @param unit
     */
    void setOldToken(String key, String value, long timeOut, TimeUnit unit);
}
