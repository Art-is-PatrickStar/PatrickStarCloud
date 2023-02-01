package com.wsw.patrickstar.aconcurrent.leetcode.Array;

/**
 * 斐波那契数列
 *
 */
public class A10 {
    //递归
    /*public int Fibonacci(int n) {
        if (n <= 0)
            return 0;

        if (n == 1)
            return 1;

        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }*/

    //循环
    public int Fibonacci(int n) {
        int a = 0;
        int b = 1;

        if (n <= 0)
            return 0;

        if (n == 1)
            return 1;

        int i = 2;
        int sum = 0;
        while (i <= n){
            sum = a + b;
            a = b;
            b = sum;
            i++;
        }

        return sum;
    }

    public static void main(String[] args) {
        A10 a10 = new A10();
        System.out.println(a10.Fibonacci(500));
    }
}
