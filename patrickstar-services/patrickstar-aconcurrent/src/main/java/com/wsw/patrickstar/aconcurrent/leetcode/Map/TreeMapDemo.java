package com.wsw.patrickstar.aconcurrent.leetcode.Map;

/*
 * TreeMap存储自定义对象
 * 提供两个排序方式，自然顺序，一个是比较器
 */

import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
        //传递一个自然比较对象进去
        TreeMap<Person,String> tm = new TreeMap<Person, String>(new PersonComparator());
        tm.put(new Person("lisi",18), "加拿大");
        tm.put(new Person("zhangsa",17), "澳大利亚");
        tm.put(new Person("zhangsa",17), "澳大利亚");
        tm.put(new Person("wangwu",20), "新加坡");
        tm.put(new Person("zhaoliu",19), "新西兰");
        tm.put(new Person("zhaoliu",19), "新西兰");
        tm.put(new Person("lisa",22), "迪拜");
        keySet(tm);
        //entrySet(tm);
    }
    /*
     * 增强for循环，遍历entrySet方式
     */
    public static void entrySet(TreeMap<Person,String> hm){
        for(Map.Entry<Person, String> entry : hm.entrySet()){
            Person p = entry.getKey();
            String value = entry.getValue();
            System.out.println(p+"..."+value);
        }
    }

    /*
     * 增强for循环，遍历keySet方式
     */
    public static void keySet(TreeMap<Person,String> tm){
        for(Person p : tm.keySet()){
            String value = tm.get(p);
            System.out.println(p+"..."+value);
        }
    }
}