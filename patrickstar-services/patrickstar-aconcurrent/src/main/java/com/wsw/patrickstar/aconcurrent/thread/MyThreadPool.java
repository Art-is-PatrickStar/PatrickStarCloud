package com.wsw.patrickstar.aconcurrent.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @Author WangSongWen
 * @Date: Created in 10:38 2020/12/24
 * @Description: 线程池
 */
@Slf4j
public class MyThreadPool {
    // FixedThreadPool---创建一个固定大小的线程池，可控制并发的线程数，超出的线程会在队列中等待
    public static void fixedThreadPool() {
        // 创建 2 个数据级的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
//        // 创建任务
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("任务被执行,线程: " + Thread.currentThread().getName());
//            }
//        };
//        // 线程池执行任务(一次添加 4 个任务)
//        // 执行任务的方法有两种:submit 和 execute
//        threadPool.submit(runnable);
//        threadPool.execute(runnable);
//        threadPool.execute(runnable);
//        threadPool.execute(runnable);

        for (int i = 0; i < 4; i++) {
            threadPool.submit(() -> {
                log.info("任务被执行,线程: " + Thread.currentThread().getName());
            });
        }
        threadPool.shutdown();
    }

    // CachedThreadPool---创建一个可缓存的线程池，若线程数超过处理所需，缓存一段时间后会回收，若线程数不够，则新建线程
    public static void cachedThreadPool() {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                log.info("任务被执行,线程: " + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }

    // SingleThreadExecutor---创建单个线程数的线程池，它可以保证先进先出的执行顺序
    public static void singleThreadExecutor() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                log.info(index + " 任务被执行");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }

    // ScheduledThreadPool---创建一个可以执行延迟任务的线程池
    public static void scheduledThreadPool() {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);

        log.info("添加任务,时间: " + new Date());
        threadPool.schedule(() -> {
            log.info("任务被执行,时间: " + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 2, TimeUnit.SECONDS);

        threadPool.shutdown();
    }

    // SingleThreadScheduledExecutor---创建一个单线程的可以执行延迟任务的线程池
    public static void singleThreadScheduledExecutor() {
        ScheduledExecutorService threadPool = Executors.newSingleThreadScheduledExecutor();

        log.info("添加任务,时间: " + new Date());
        threadPool.schedule(() -> {
            log.info("任务被执行,时间: " + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 2, TimeUnit.SECONDS);

        threadPool.shutdown();
    }

    // newWorkStealingPool---创建一个抢占式执行的线程池（任务执行顺序不确定），此方法只有在 JDK 1.8+ 版本中才能使用
    public static void newWorkStealingPool() {
        ExecutorService threadPool = Executors.newWorkStealingPool();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                log.info(index + " 被执行,线程名: " + Thread.currentThread().getName());
            });
        }
        // 确保任务执行完成
        while (!threadPool.isTerminated()) {

        }
        threadPool.shutdown();
    }

    // ThreadPoolExecutor---最原始的创建线程池的方式，它包含了 7 个参数可供设置
    public static void threadPoolExecutor() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 100, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));

        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                log.info(index + " 被执行,线程名: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }

    public static void MyThreadPoolExecutor() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1,
                1,
                100,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1),
                new ThreadPoolExecutor.AbortPolicy()  // 拒绝策略
                // 自定义拒绝策略
//                new RejectedExecutionHandler() {
//                    @Override
//                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//
//                    }
//                }
        );

        for (int i = 0; i < 4; i++) {
            threadPool.execute(() -> {
                log.info("当前任务被执行,时间: " + new Date() + " 执行线程: " + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }

    public static void main(String[] args) {
        //MyThreadPool.fixedThreadPool();
        //MyThreadPool.cachedThreadPool();
        //MyThreadPool.singleThreadExecutor();
        //MyThreadPool.scheduledThreadPool();
        //MyThreadPool.singleThreadScheduledExecutor();
        //MyThreadPool.newWorkStealingPool();
        //MyThreadPool.threadPoolExecutor();
        MyThreadPool.MyThreadPoolExecutor();
    }
}
