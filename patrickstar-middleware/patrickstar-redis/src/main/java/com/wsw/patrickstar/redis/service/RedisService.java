package com.wsw.patrickstar.redis.service;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/15 15:25
 */
public interface RedisService {
    /**
     * 设置缓存
     *
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     * @return void
     */
    void setValue(String key, String value, Long time, TimeUnit timeUnit);

    /**
     * 获取缓存
     *
     * @param key
     * @return String
     */
    String getValue(String key);
}
