package com.wsw.patrickstar.aconcurrent.leetcode.Map;

import java.util.HashMap;
import java.util.Map;
/*
 * for循环遍历间接遍历map
 */
public class MapDemo2 {
    public static void main(String[] args) {

        Map<String, Integer> m = new HashMap<>();
        m.put("a", 1);
        m.put("b", 2);
        m.put("c", 3);

        //1.利用普通的set方法for循环遍历，平时中推荐使用
        for(String s : m.keySet()){
            System.out.println(s+"----" +  m.get(s));
        }
        //2.同样，使用内部类来进行实现
        for(Map.Entry<String, Integer> entry : m.entrySet()){
            System.out.println(entry.getKey() + "..."+  entry.getValue());
        }
    }
}
