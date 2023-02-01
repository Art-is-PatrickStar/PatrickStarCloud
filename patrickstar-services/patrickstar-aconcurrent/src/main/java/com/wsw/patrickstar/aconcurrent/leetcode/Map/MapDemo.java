package com.wsw.patrickstar.aconcurrent.leetcode.Map;

/*
 * Map中的方法
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
public class MapDemo {
    public static void main(String[] args) {
        //method();
        //method1();
        //method2();
        //method3();
        //method4();
        method5();
    }
    /*
     * put(k,v)将键值存储到集合中
     */
    public static void method(){
        Map<String,Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 1);
        map.put("c", 1);
        map.put("d", 4);
        map.put("e", 6);
        System.out.println(map);
    }

    /*
     * putAll(Map map)把一个集合全部插入另一个集合
     *
     */
    public static void method1(){
        Map<String,Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 1);
        map.put("c", 1);

        Map<String,Integer> map2 = new HashMap<>();
        map2.put("e", 2);
        map2.put("f", 3);
        map2.put("g", 4);

        map.putAll(map2);

        System.out.println(map);
    }

    /*
     * V get(Key) 传递一个键，返回键对应的值，如果此键不存在，返回为空
     */
    public static void method2(){
        Map<String,Integer> map = new HashMap<>();
        map.put("a", 234);
        map.put("b", 1);
        map.put("c", 1);
        System.out.println(map.get("z"));
    }

    /*
     * void clear() 清除所有对象
     * void isEmpty() 判断是为空
     * int size() 返回映射关系对的个数
     * boolean containsKey(Key) 判断键是否存在，存在返回真，否则返回假
     * boolean containsValue(Value) 同理判断值是否存在，存在返回真，不存在返回假
     */
    public static void method3(){
        Map<String,Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 123);
        map.put("c", 124);
        System.out.println(map.size());
        System.out.println(map.containsKey("A"));
        //自动装箱，会调用equals判断，所以为真
        System.out.println(map.containsValue(new Integer(124)));
        map.clear();
        System.out.println(map.isEmpty());
    }

    /*
     * V remove(K)
     * 移除指定的键值对，传递键，返回值
     * 没有移除成功返回null
     */
    public static void method4(){
        Map<String,Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 123);
        map.put("c", 124);
        System.out.println(map.remove("a"));
        System.out.println(map);
    }

    /*
     * Collection<V> values()将Map中的所有值，存储到Collection集合
     */
    public static void method5(){
        Map<String,Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 123);
        map.put("c", 124);

        Collection<Integer> c = new ArrayList<>();

        c = map.values();
        System.out.println(map);
        System.out.println(c);
    }

}
