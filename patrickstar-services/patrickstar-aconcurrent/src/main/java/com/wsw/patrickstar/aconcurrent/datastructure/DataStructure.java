package com.wsw.patrickstar.aconcurrent.datastructure;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @Author WangSongWen
 * @Date: Created in 10:07 2021/3/9
 * @Description: list: 可重复
 * set: 不重复
 */
public class DataStructure {
    public void mapTest() {
        HashMap<String, Object> hashMap = new HashMap<>(); // 无序 线程不安全
        TreeMap<String, Object> treeMap = new TreeMap<>(); // 排序
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>(); // 有序 线程不安全
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>(); // 线程安全
    }

    public void listTest() {
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();
    }

    public void setTest() {
        HashSet<String> hashSet = new HashSet<>(); // 哈希表
        TreeSet<String> treeSet = new TreeSet<>(); // 二叉树 排序
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(); // 有序
    }

    public static void per() {
        Date perDate = new Date();
        int i = 0, x, y;
        Random random;
        double ans;
        while (i < 100000000) {
            random = new Random();
            x = random.nextInt(20);
            y = random.nextInt(100);
            if (x >= 13) {
                System.out.println("48000");
            } else if (x >= 11) {
                System.out.println("36000");
            } else if (x >= 8) {
                System.out.println("24000");
            } else if (x >= 6) {
                System.out.println("18000");
            } else if (x == 5 || (x == 4 && y > 30) || (x == 3 && y > 60)) {
                System.out.println("12000");
            } else {
                ans = (6 * (Math.pow(2, 2 + x)) * y);
                System.out.println(ans);
            }
            i++;
        }
        Date newDate = new Date();
        System.out.println(newDate.getTime() - perDate.getTime());
    }

    public static void per2() {
        Date perDate = new Date();
        IntStream.range(0, 100000000).forEach(i -> {
            Random random = new Random();
            int x = random.nextInt(20);
            int y = random.nextInt(100);
            if (x >= 13) {
                System.out.println("48000");
            } else if (x >= 11) {
                System.out.println("36000");
            } else if (x >= 8) {
                System.out.println("24000");
            } else if (x >= 6) {
                System.out.println("18000");
            } else if (x == 5 || (x == 4 && y > 30) || (x == 3 && y > 60)) {
                System.out.println("12000");
            } else {
                System.out.println((6 * (Math.pow(2, 2 + x)) * y));
            }
        });
        Date newDate = new Date();
        System.out.println(newDate.getTime() - perDate.getTime());
    }

    public void mapFor() {
        Map<String, Object> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        for (Map.Entry<String, Object> next : map.entrySet()) {
            Object value = next.getValue();
            System.out.println(value);
        }
    }

    public static void main(String[] args) {
        per2();
    }

}
