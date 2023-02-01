package com.wsw.patrickstar.aconcurrent.leetcode.Array;

/**
 * 青蛙跳台阶
 *
 */
public class A11 {
    public int JumpFloor(int target) {
        int a = 1;
        int b = 2;

        if (target <= 0)
            return -1;

        if (target == 1)
            return 1;

        if (target == 2)
            return 2;

        int sum = a + b;
        for (int i = 3; i <= target; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }

        return sum;
    }

    //变态跳台阶
    /*public int JumpFloorII(int target) {
        return (int)Math.pow(2,target-1);
    }*/

    public static void main(String[] args) {
        A11 a11 = new A11();
        System.out.println(a11.JumpFloor(3));
        System.out.println(a11.JumpFloor(5));
        System.out.println(a11.JumpFloor(10));
        System.out.println(a11.JumpFloor(0));
        System.out.println(a11.JumpFloor(40));
        System.out.println(a11.JumpFloor(100));
    }
}
