package com.wsw.patrickstar.aconcurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luzh32717
 * @version 1.0
 * @date 2021-12-01 16:54
 */
public class PrintNum {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition conditionX = lock.newCondition();
    private static final Condition conditionY = lock.newCondition();
    private static int i = 0;

    public static void run() {
        new Thread(() -> {
            while (i < 100) {
                try {
                    lock.lock();
                    if (i % 2 != 0) {
                        conditionX.await();
                    }
                    System.out.println("printX: " + i++);
                    conditionY.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
        new Thread(() -> {
            while (i < 100) {
                try {
                    lock.lock();
                    if (i % 2 == 0) {
                        conditionY.await();
                    }
                    System.out.println("printY: " + i++);
                    conditionX.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new PrintNum().run();
    }
}
