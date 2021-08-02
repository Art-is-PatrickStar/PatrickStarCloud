package com.wsw.patrickstar.controller;

import com.wsw.patrickstar.service.RedisService;
import com.wsw.patrickstar.util.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author WangSongWen
 * @Date 2021/8/1 23:36
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Resource
    private RedisService redisService;

    @GetMapping("/tryLock")
    public RedisLock tryLock(@RequestParam("lockKey") String lockKey) {
        return redisService.tryLock(lockKey);
    }

    @PostMapping("/setRefreshToken")
    public void setRefreshToken(@RequestParam("hashName") String hashName, @RequestParam("hashKey") String hashKey,
                                @RequestParam("hashValue") String hashValue) {
        redisService.setRefreshToken(hashName, hashKey, hashValue);
    }

    @PostMapping("/getRefreshToken")
    public String getRefreshToken(@RequestParam("hashName") String hashName, @RequestParam("hashKey") String hashKey) {
        return redisService.getRefreshToken(hashName, hashKey);
    }

    @PostMapping("/setRefreshTokenExpire")
    public void setRefreshTokenExpire(@RequestParam("hashName") String hashName, @RequestParam("timeOut") long timeOut,
                                      @RequestParam("unit") TimeUnit unit) {
        redisService.setRefreshTokenExpire(hashName, timeOut, unit);
    }

    @PostMapping("/setOldToken")
    void setOldToken(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("timeOut") long timeOut,
                     @RequestParam("unit") TimeUnit unit) {
        redisService.setOldToken(key, value, timeOut, unit);
    }
}
