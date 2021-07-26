package com.wsw.patrickstar.service;

import org.redisson.api.RLock;

/**
 * @Author WangSongWen
 * @Date 2021/7/25 22:52
 * @Description: redis服务接口
 */
public interface RedisService {
    RLock tryLock(String key);
}
