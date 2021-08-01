package com.wsw.patrickstar.controller;

import com.wsw.patrickstar.service.RedisService;
import com.wsw.patrickstar.util.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    RedisLock tryLock(@RequestParam("lockKey") String lockKey) {
        return redisService.tryLock(lockKey);
    }
}
