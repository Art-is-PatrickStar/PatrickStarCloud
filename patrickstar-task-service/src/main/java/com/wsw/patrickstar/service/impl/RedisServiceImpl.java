package com.wsw.patrickstar.service.impl;

import com.wsw.patrickstar.Constants.RedisConstants;
import com.wsw.patrickstar.service.RedisService;
import com.wsw.patrickstar.util.RedisDistributedLock;
import com.wsw.patrickstar.util.RedisLock;
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
