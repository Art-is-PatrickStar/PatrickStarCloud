package com.wsw.patrickstar.common.constant;

import java.util.concurrent.TimeUnit;

/**
 * @Author WangSongWen
 * @Date 2021/7/26 23:19
 * @Description:
 */
public class RedisConstants {
    public static final String REDIS_LOCK_PREFIX = "patrickstar:lock:";

    public static final int REDIS_LOCK_WAIT_TIME = 5;

    public static final int REDIS_LOCK_LEASE_TIME = 180;

    public static final TimeUnit REDIS_LOCK_TIME_UNIT = TimeUnit.SECONDS;
}
