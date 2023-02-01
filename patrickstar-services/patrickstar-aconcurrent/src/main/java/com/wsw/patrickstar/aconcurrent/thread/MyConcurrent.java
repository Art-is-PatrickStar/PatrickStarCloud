package com.wsw.patrickstar.aconcurrent.thread;

/**
 * @Author WangSongWen
 * @Date: Created in 14:02 2021/3/10
 * @Description: volatile synchronized ThreadLocal
 */
public class MyConcurrent {
    private volatile int count; // volatile保证变量的可见性 不能保证原子性

    public int getCount(){
        return count;
    }

    public synchronized void increment(){
        count++; // synchronized保证变量的可见性 并能保证原子性（加锁 同步）时间换空间
    }

    // ThreadLocal为每个线程创建变量副本 空间换时间
    private static final ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new MyThread(i)).start();
        }
    }

    public static class MyThread implements Runnable {
        private final int i;

        public MyThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println("线程" + i + "初始值: " + integerThreadLocal.get());
            for (int j = 0; j < 10; j++) {
                integerThreadLocal.set(integerThreadLocal.get() + j);
            }
            System.out.println("线程" + i + "累计值: " + integerThreadLocal.get());
        }
    }
}
