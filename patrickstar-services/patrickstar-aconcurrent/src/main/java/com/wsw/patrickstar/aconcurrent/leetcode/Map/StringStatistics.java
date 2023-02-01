package com.wsw.patrickstar.aconcurrent.leetcode.Map;

import java.util.HashMap;
import java.util.Map;

/*
 * 练习：
 * 统计字符串里面每个字符出现的次数，存储到map里面
 * eg：a=1,b=3....
 *
 */
public class StringStatistics {
    public static Map<Character, Integer> statistics(String s){
        Map<Character, Integer> hashMap = new HashMap<>();
        char[] c = s.toCharArray();

        for (int i = 0; i < c.length; i++) {
            char temp = c[i];
            if (hashMap.containsKey(temp)){
                hashMap.put(temp, hashMap.get(temp)+1);
            }else
                hashMap.put(temp, 1);
        }

        return hashMap;
    }

    public static void main(String[] args) {
        String s = "adassjfsanfjksanfoqnfioe";
        System.out.println(statistics(s));
    }
}
