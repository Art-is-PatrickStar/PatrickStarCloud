package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 * 捡绳子
 *
 */
public class N4 {
    //动态规划——时间复杂度O(n^2) 空间复杂度O(n)
    /*public int maxAfterCutting(int length){
        if (length < 2)
            return 0;
        if (length == 2)
            return 1;
        if (length == 3)
            return 2;

        int[] temp = new int[length + 1];
        temp[0] = 0;
        temp[1] = 1;
        temp[2] = 2;
        temp[3] = 3;
        int result = 0;
        for (int i = 4; i <= length; i++) {
            int max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int num = temp[j] * temp[i - j];
                if (num > max)
                    max = num;
            }
            temp[i] = max;
        }
        result = temp[length];

        return result;
    }*/

    //贪婪算法——时间复杂度和空间复杂度均为O(1)
    public int maxAfterCutting(int length){
        if (length < 2)
            return 0;
        if (length == 2)
            return 1;
        if (length == 3)
            return 2;

        int timesOf3 = length / 3;
        if (length - timesOf3 * 3 == 1)
            timesOf3 -= 1;

        int timesOf2 = (length - timesOf3 * 3) / 2;

        return (int) (Math.pow(3, timesOf3) * Math.pow(2, timesOf2));
    }

    public static void main(String[] args) {
        N4 n4 = new N4();
        System.out.println(n4.maxAfterCutting(4));
        System.out.println(n4.maxAfterCutting(7));
        System.out.println(n4.maxAfterCutting(8));
    }
}
