package com.wsw.patrickstar.aconcurrent.leetcode.Map;

/*
 * HashMap集合的存取自定义对象
 * Person是之前自定义的对象
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<Person,String> hm = new HashMap<>();

        hm.put(new Person("张三",22), "加拿大");
        hm.put(new Person("李四",42), "澳大利亚");
        hm.put(new Person("王五",71), "美国");
        hm.put(new Person("赵六",54), "英国");

        method1(hm);
    }

    /*
     * 实现map集合的第一种获取方法
     */
    public static void method(HashMap<Person,String> hm){
        Set<Person> set  = hm.keySet();
        Iterator<Person> it = set.iterator();

        while(it.hasNext()){
            Person p = it.next();
            String s = hm.get(p);
            System.out.println(p + ".." + s);
        }
    }

    /*
     * 第二种方法获取，entry
     */
    public static void method1(HashMap<Person,String> hm){
        Set<Map.Entry<Person, String>> set = hm.entrySet();
        Iterator<Map.Entry<Person, String>> it = set.iterator();

        while(it.hasNext()){
            Map.Entry<Person, String> e = it.next();
            System.out.println(e.getKey() + ".." + e.getValue());
        }
    }
}