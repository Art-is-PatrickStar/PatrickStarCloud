package com.wsw.patrickstar.aconcurrent.leetcode;

/**
 * @Author WangSongWen
 * @Date: Created in 10:16 2021/3/16
 * @Description: dp 爬楼梯
 */
public class Leetcode70 {
    public int climbStairs(int n) {
        if (n <= 2)
            return n;
        int pre = 1;
        int next = 2;
        int res = 0;
        for (int i = 2; i < n; i++) {
            res = pre + next;
            pre = next;
            next = res;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Leetcode70().climbStairs(4));
    }
}
