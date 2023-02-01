package com.wsw.patrickstar.aconcurrent.leetcode.qiuzhao.xiaomi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wsw on 2019/9/7 09:51
 * 异数 ———— 进制转换
 */
public class DNumber {
    static class Pair{
        String key;
        int value;

        public Pair(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;

        // 存储字符串和它对应的十进制数
        ArrayList<Pair> list = new ArrayList<>();
        // 存储十进制数值和它出现的次数
        Map<Integer, Integer> map = new HashMap<>();

        while (!(str = bf.readLine()).equals("END")) {
            int num = getValue(str);
            list.add(new Pair(str, num));
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        boolean result = false;
        for (int i = 0; i < list.size(); i++) {
            if (map.get(list.get(i).value) == 1){
                result = true;
                System.out.println(list.get(i).key);
            }
        }

        if (!result){
            System.out.println("None");
        }
    }

    //将其他进制转化为十进制
    public static int getValue(String s){
        String[] strings = s.split("#");
        int key = Integer.parseInt(strings[0]); //进制
        int value = Integer.parseInt(strings[1], key); //转化后的十进制数值
        return value;
    }
}
