package com.wsw.patrickstar.service;

import com.wsw.patrickstar.util.RedisLock;

/**
 * @Author WangSongWen
 * @Date 2021/7/25 22:52
 * @Description: redis服务接口
 */
public interface RedisService {
    RedisLock tryLock(String key);
}
