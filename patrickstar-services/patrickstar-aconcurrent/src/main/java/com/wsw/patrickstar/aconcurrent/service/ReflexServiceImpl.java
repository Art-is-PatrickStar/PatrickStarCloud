package com.wsw.patrickstar.aconcurrent.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * @Author WangSongWen
 * @Date: Created in 14:24 2021/3/9
 * @Description:
 */
@Service
public class ReflexServiceImpl {
    public String toString(String a, String b) {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(a);
        hashSet.add(a);
        hashSet.add(b);
        hashSet.add(b);
        return hashSet.toString();
    }

    public void sayHello(String a, String b) {
        System.out.println("hello: " + a + ", " + b);
    }
}
