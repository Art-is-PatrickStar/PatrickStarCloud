package com.wsw.patrickstar.service.impl;

import com.wsw.patrickstar.service.RedisService;
import com.wsw.patrickstar.util.RedisLock;
import org.springframework.stereotype.Service;

/**
 * @Author WangSongWen
 * @Date 2021/7/26 00:08
 * @Description:
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Override
    public RedisLock tryLock(String key) {
        return null;
    }
}
