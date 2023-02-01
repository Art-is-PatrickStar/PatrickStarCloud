package com.wsw.patrickstar.aconcurrent.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author WangSongWen
 * @Date: Created in 13:47 2020/12/29
 * @Description:
 */
public class StopThread {
    /*private static boolean stopFlag;

    private static synchronized void setStopFlag() {
        stopFlag = true;
    }

    private static synchronized boolean isStopFlag() {
        return stopFlag;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!isStopFlag()) {
                i++;
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        setStopFlag();
    }*/

    private static volatile boolean stopFlag;

    private static final AtomicLong nextNum = new AtomicLong();

    private static long getNum() {
        return nextNum.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stopFlag) {
                i++;
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        stopFlag = true;
    }
}
