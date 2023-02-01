package com.wsw.patrickstar.aconcurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author WangSongWen
 * @Date: Created in 9:59 2021/3/16
 * @Description:
 */
public class WaitNotify {
    public synchronized void before() {
        System.out.println("before");
        notifyAll();
    }

    public synchronized void after() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        WaitNotify waitNotify = new WaitNotify();
        executorService.submit(()-> waitNotify.after());
        executorService.submit(()-> waitNotify.before());
    }
}
