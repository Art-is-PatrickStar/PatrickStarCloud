package com.wsw.patrickstar.aconcurrent.leetcode.Map;

import java.util.*;

public class MapDemo1 {
    /*
     * 练习map的两种数据取出方式
     */
    public static void main(String[] args) {
        //method();
        method1();
    }

    /*
     * 利用set取出
     */
    public static void method(){
        Map<String, Integer> map = new HashMap<>();

        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        map.put("d",4);

        Set<String> set = map.keySet();

        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            System.out.println(map.get(it.next()));
        }
    }

    /*
     * 利用集合的键值对关系对象获取 entrySet,这是一个内部类
     */
    public static void method1(){
        Map<String, Integer> map = new HashMap<>();

        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        map.put("d",4);

        Set<Map.Entry<String, Integer>> o = new HashSet<>();

        o = map.entrySet();

        Iterator<Map.Entry<String, Integer>> it = o.iterator();

        while(it.hasNext()){
            //利用多态，next返回实现类对象给接口，可以不用管具体的实现类原理
            Map.Entry<String, Integer> entry = it.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}