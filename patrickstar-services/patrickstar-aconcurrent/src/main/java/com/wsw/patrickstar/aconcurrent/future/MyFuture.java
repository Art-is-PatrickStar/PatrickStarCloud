package com.wsw.patrickstar.aconcurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: wangsongwen
 * @Date: 2021/10/27 16:12
 * @Description:
 */
public class MyFuture {
    ExecutorService executor = Executors.newFixedThreadPool(5);

    public String getName() {
        return "name";
    }

    public Integer getAge() {
        return 21;
    }

    public String getLocation() {
        return "location";
    }

    public String getAllFormat() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(this::getName, executor);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(this::getAge, executor);
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(this::getLocation, executor);
        CompletableFuture.allOf(future1, future2, future3).join();
        try {
            String name = future1.get();
            Integer age = future2.get();
            String location = future3.get();
            return name + "," + age + "," + location;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new MyFuture().getAllFormat());
    }
}
