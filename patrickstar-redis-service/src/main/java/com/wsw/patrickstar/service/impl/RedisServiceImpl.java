package com.wsw.patrickstar.service.impl;

import com.wsw.patrickstar.Constants.RedisConstants;
import com.wsw.patrickstar.service.RedisService;
import com.wsw.patrickstar.util.RedisDistributedLock;
import com.wsw.patrickstar.util.RedisLock;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisDistributedLock redisDistributedLock;

    @Override
    public RedisLock tryLock(String lockKey) {
        return redisDistributedLock.tryLock(RedisConstants.REDIS_LOCK_PREFIX + lockKey,
                RedisConstants.REDIS_LOCK_WAIT_TIME, RedisConstants.REDIS_LOCK_LEASE_TIME, RedisConstants.REDIS_LOCK_TIME_UNIT);
    }

    @Override
    public void setRefreshToken(String hashName, String hashKey, String hashValue) {
        stringRedisTemplate.opsForHash().put(hashName, hashKey, hashKey);
    }

    @Override
    public String getRefreshToken(String hashName, String hashKey) {
        return (String) stringRedisTemplate.opsForHash().get(hashName, hashKey);
    }

    @Override
    public void setRefreshTokenExpire(String hashName, long timeOut, TimeUnit unit) {
        stringRedisTemplate.expire(hashName, timeOut, unit);
    }

    @Override
    public void setOldToken(String key, String value, long timeOut, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeOut, unit);
    }
}
