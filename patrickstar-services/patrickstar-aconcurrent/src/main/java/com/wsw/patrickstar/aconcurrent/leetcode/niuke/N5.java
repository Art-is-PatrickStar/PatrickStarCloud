package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 * 二进制中1的个数
 *
 */
public class N5 {
    public int NumberOf1(int n) {
        int count = 0;

        while (n != 0){
            ++count;
            n = (n - 1) & n;
        }

        return count;
    }

    public static void main(String[] args) {
        N5 n5 = new N5();
        System.out.println(n5.NumberOf1(9));
        System.out.println(n5.NumberOf1(-5));
    }
}
