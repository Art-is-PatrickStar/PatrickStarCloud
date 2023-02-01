package com.wsw.patrickstar.aconcurrent.leetcode.Array;

/**
 * 矩形覆盖
 *
 */
public class A12 {
    public int RectCover(int target) {
        if (target == 1)
            return 1;
        if (target == 2)
            return 2;
        int a = 1;
        int b = 2;
        int sum = 0;
        for (int i = 3; i <= target ; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }

        return sum;
    }

    public static void main(String[] args) {
        A12 a12 = new A12();
        System.out.println(a12.RectCover(4));
    }
}
