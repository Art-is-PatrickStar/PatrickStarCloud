package com.wsw.patrickstar.aconcurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author WangSongWen
 * @Date: Created in 9:15 2021/3/16
 * @Description:
 */
public class SyncThread {
    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        }
    }

    public synchronized void func2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    public void func3() {
        synchronized (SyncThread.class) {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        SyncThread syncThread1 = new SyncThread();
        SyncThread syncThread2 = new SyncThread();
        /*new Thread(()->{
            syncThread1.func1();
            syncThread2.func1();
        }).start();*/
        ExecutorService executorService = Executors.newCachedThreadPool();
        /*executorService.submit(() -> {
            syncThread1.func1();
        });
        executorService.submit(() -> {
            syncThread1.func1();
        });*/
        /*executorService.execute(syncThread1::func1);
        executorService.execute(syncThread2::func1);*/
        /*executorService.submit(() -> {
            syncThread1.func2();
        });
        executorService.submit(() -> {
            syncThread1.func2();
        });*/
        /*executorService.execute(() -> {
            syncThread1.func2();
        });
        executorService.execute(() -> {
            syncThread2.func2();
        });*/
    }
}
