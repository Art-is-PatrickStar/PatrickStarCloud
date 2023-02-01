package com.wsw.patrickstar.aconcurrent.leetcode.fanshe;

import java.lang.reflect.Array;

/**
 * Created by wsw on 2019/9/24 14:49
 * 通过反射创建数组
 */
public class arrayClass {
    public static void testArray() throws ClassNotFoundException {
        Class mClass = Class.forName("java.lang.String");
        Object array = Array.newInstance(mClass, 25);
        Array.set(array, 0, "hello");
        Array.set(array, 1, "world");
        System.out.println(Array.get(array, 1));
    }

    public static void main(String[] args) throws ClassNotFoundException {
        testArray();
    }
}
