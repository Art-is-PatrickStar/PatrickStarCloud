package com.wsw.patrickstar.task.service;

import com.wsw.patrickstar.task.redis.RedisLock;

/**
 * @Author WangSongWen
 * @Date 2021/7/25 22:52
 * @Description: redis服务接口
 */
public interface RedisService {
    /**
     * 获取分布式锁接口
     *
     * @param key
     * @return
     */
    RedisLock tryLock(String key);
}
