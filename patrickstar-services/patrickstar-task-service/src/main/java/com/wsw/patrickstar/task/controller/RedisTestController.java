package com.wsw.patrickstar.task.controller;

import com.wsw.patrickstar.redis.lock.RedisDistributedLock;
import com.wsw.patrickstar.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/15 13:57
 */
@Slf4j
@RestController
public class RedisTestController {
    @Resource
    private RedisDistributedLock redisDistributedLock;
    @Resource
    private RedisService redisService;

    @GetMapping("/redis/lock/test")
    public void redisLockTest() throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                redisDistributedLock.tryLock("locktest",5,10, TimeUnit.SECONDS, () -> {
                    System.out.println("执行开始..." + Thread.currentThread().getName());
                    try {
                        Thread.sleep(4*1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("执行结束..." + Thread.currentThread().getName());
                    return true;
                });
            }).start();
        }
        Thread.sleep(1000000L);
    }

    @GetMapping("/redis/cache/set")
    public void redisCacheSet() {
        redisService.setValue("cachetest", "wsw", 1L, TimeUnit.DAYS);
    }

    @GetMapping("/redis/cache/get")
    public String redisCacheGet() {
        return redisService.getValue("cachetest");
    }
}
