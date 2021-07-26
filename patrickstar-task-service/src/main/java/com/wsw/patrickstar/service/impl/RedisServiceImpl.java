package com.wsw.patrickstar.service.impl;

import com.wsw.patrickstar.Constants.RedisConstants;
import com.wsw.patrickstar.service.RedisService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author WangSongWen
 * @Date 2021/7/26 00:08
 * @Description: redis服务实现类
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedissonClient redissonClient;

    @Override
    public RLock tryLock(String key) {
        RLock lock = redissonClient.getLock(RedisConstants.REDIS_LOCK_PREFIX + key);
        lock.lock(RedisConstants.REDIS_LOCK_LEASE_TIME, TimeUnit.SECONDS);
        return lock;
    }
}
