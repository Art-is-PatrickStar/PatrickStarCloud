package com.wsw.patrickstar.aconcurrent.leetcode.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetTest {
    public static void main(String[] agrs){
        //创建HashSet集合：
        Set<String> hashSet = new HashSet<String>();
        System.out.println("HashSet初始容量大小："+hashSet.size());

        //元素添加：
        hashSet.add("my");
        hashSet.add("name");
        hashSet.add("is");
        hashSet.add("wsw");
        hashSet.add(",");
        hashSet.add("hello");
        hashSet.add("world");
        hashSet.add("!");
        System.out.println("HashSet容量大小："+hashSet.size());

        //迭代器遍历：
        Iterator<String> iterator = hashSet.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            System.out.println(str);
        }

        //增强for循环
        for(String str:hashSet){
            if("wsw".equals(str)){
                System.out.println("你就是我想要的元素:"+str);
            }
            System.out.println(str);
        }

        //元素删除：
        hashSet.remove("wsw");
        System.out.println("HashSet元素大小：" + hashSet.size());
        hashSet.clear();
        System.out.println("HashSet元素大小：" + hashSet.size());

        //集合判断：
        boolean isEmpty = hashSet.isEmpty();
        System.out.println("HashSet是否为空：" + isEmpty);
        boolean isContains = hashSet.contains("hello");
        System.out.println("HashSet是否为空：" + isContains);
    }
}