package com.wsw.patrickstar.aconcurrent.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 16:21 2021/3/15
 * @Description: Fibonacci dp
 */
public class Fib {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long result = new Fib().fib3(100);
        long end = System.currentTimeMillis();
        System.out.println(result + " " + start + " " + end + " " + (end - start));
    }

    Map<Integer, Integer> map = new HashMap<>();

    public int fib1(int n) {
        if (n == 1)
            return 1;
        else if (n == 2)
            return 2;
        else
            return fib1(n - 1) + fib1(n - 2);
    }

    public int fib2(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (map.get(n) != null) {
            return map.get(n);
        }
        int result = fib1(n - 1) + fib1(n - 2);
        map.put(n, result);
        return result;
    }

    public long fib3(int n) {
        long pre = 1, next = 2, result = 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        for (int i = 3; i < n + 1; i++) {
            result = pre + next;
            pre = next;
            next = result;
        }
        return result;
    }

    private static int[][] triangle = {
            {2, 0, 0, 0},
            {3, 4, 0, 0},
            {6, 5, 7, 0},
            {4, 1, 8, 3}
    };
}
