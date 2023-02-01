package com.wsw.patrickstar.aconcurrent.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author WangSongWen
 * @Date: Created in 16:29 2020/12/30
 * @Description:
 */
@Service
public class AsyncService {
    @Async("wswExecutor")
    public void executeAsync1() throws InterruptedException {
        System.out.println("MsgServer send A thread name -> " + Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(2);
        long end = System.currentTimeMillis();
        System.out.println("MsgServer send A 耗时" + (end - start) + "ms");
    }

    @Async("wswExecutor")
    public void executeAsync2() throws InterruptedException {
        System.out.println("MsgServer send B thread name -> " + Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(2);
        long end = System.currentTimeMillis();
        System.out.println("MsgServer send B 耗时" + (end - start) + "ms");
    }
}
