package com.wsw.patrickstar.aconcurrent.schedule;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author WangSongWen
 * @Date: Created in 16:29 2020/12/30
 * @Description:
 */
@RestController
public class AsyncController {
    @Resource
    private AsyncService asyncService;

    @GetMapping("/start")
    public void start() throws InterruptedException {
        System.out.println("主线程 name -> " + Thread.currentThread().getName());
        asyncService.executeAsync1();
        asyncService.executeAsync2();
    }
}
