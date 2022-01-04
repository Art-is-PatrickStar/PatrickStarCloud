package com.wsw.patrickstar.task.service.impl;

import com.wsw.patrickstar.task.constants.RedisConstants;
import com.wsw.patrickstar.task.service.RedisService;
import com.wsw.patrickstar.task.redis.RedisDistributedLock;
import com.wsw.patrickstar.task.redis.RedisLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author WangSongWen
 * @Date 2021/7/26 00:08
 * @Description: redis服务实现类
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisDistributedLock redisDistributedLock;

    @Override
    public RedisLock tryLock(String key) {
        return redisDistributedLock.tryLock(RedisConstants.REDIS_LOCK_PREFIX + key,
                RedisConstants.REDIS_LOCK_WAIT_TIME, RedisConstants.REDIS_LOCK_LEASE_TIME, RedisConstants.REDIS_LOCK_TIME_UNIT);
    }
}
