package com.wsw.patrickstar.aconcurrent.leetcode.Hash;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 */
public class H3 {
//    public String frequencySort(String s) {
//        Map<Character,Integer> map = new HashMap<>();
//
//        char[] chars = s.toCharArray();
//        for (Character c : chars){
//            if (map.containsKey(c)){
//                map.put(c, map.get(c) + 1);
//            }
//            else {
//                map.put(c, 1);
//            }
//        }
//
//        List<Character>[] bucket = new ArrayList[s.length() + 1];
//        for (char k : map.keySet()){
//            int v = map.get(k);
//            if (bucket[v] == null)
//                bucket[v] = new ArrayList<>();
//            bucket[v].add(k);
//        }
//
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = bucket.length - 1; i > 0; i--) {
//            if (bucket[i] != null){
//                for (char c : bucket[i]){
//                    for (int j = 0; j < map.get(c); j++) {
//                        stringBuilder.append(c);
//                    }
//                }
//            }
//        }
//
//        return stringBuilder.toString();
//    }

    public String frequencySort(String s) {
        Map<Character,Integer> map = new HashMap<>();

        char[] chars = s.toCharArray();
        for (Character c : chars){
            if (map.containsKey(c)){
                map.put(c, map.get(c) + 1);
            }
            else {
                map.put(c, 1);
            }
        }

        PriorityQueue<Map.Entry<Character, Integer>> priorityQueue = new PriorityQueue<>(
                new Comparator<Map.Entry<Character, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                        return o2.getValue() - o1.getValue();
                    }
                }
        );

        priorityQueue.addAll(map.entrySet());
        StringBuilder stringBuilder = new StringBuilder();

        while (!priorityQueue.isEmpty()){
            Map.Entry<Character, Integer> e = priorityQueue.poll();
            for (int i = 0; i < e.getValue(); i++) {
                stringBuilder.append(e.getKey());
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        H3 h = new H3();
        String s = "aabbb";
        String res = h.frequencySort(s);
        System.out.println(res);
    }
}
