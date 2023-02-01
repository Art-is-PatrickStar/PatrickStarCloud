package com.wsw.patrickstar.aconcurrent.thread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @Author WangSongWen
 * @Date: Created in 14:28 2020/12/28
 * @Description: ThreadLocal
 * <p>
 * get()方法是用来获取ThreadLocal在当前线程中保存的变量副本
 * set()用来设置当前线程中变量的副本
 * remove()用来移除当前线程中变量的副本
 * initialValue()是一个protected方法,一般是用来在使用时进行重写
 * <p>
 * 在进行get之前,必须先set,否则会报空指针异常,如果想在get之前不需要调用set就能正常访问的话,必须重写initialValue()方法
 */
public class MyThreadLocal {
    private static final ThreadLocal<DateFormat> DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private static final ThreadLocal<String> STRING_THREAD_LOCAL = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };

    private static final ThreadLocal<Long> LONG_THREAD_LOCAL = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    };

    public void set() {
        STRING_THREAD_LOCAL.set(Thread.currentThread().getName());
        LONG_THREAD_LOCAL.set(Thread.currentThread().getId());
    }

    public Long getLong() {
        return LONG_THREAD_LOCAL.get();
    }

    public String getString() {
        return STRING_THREAD_LOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        //System.out.println(DATE_FORMAT_THREAD_LOCAL.get().format(System.currentTimeMillis()));
        final MyThreadLocal myThread = new MyThreadLocal();
        myThread.set();
        System.out.println(myThread.getLong());
        System.out.println(myThread.getString());

        Thread thread = new Thread(() -> {
            myThread.set();
            System.out.println(myThread.getLong());
            System.out.println(myThread.getString());
        });
        thread.start();
        thread.join();

        System.out.println(myThread.getLong());
        System.out.println(myThread.getString());
    }
}
