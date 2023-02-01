package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 * Created by wsw on 2019/9/4 19:17
 * 连续子数组的最大和
 */
public class N29 {
    /*public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0)
            return 0;

        int curSum = 0;
        int greatestSum=0x80000000; //这是32位数最小值，负2的32次方
        for (int i = 0; i < array.length; i++) {
            if (curSum <= 0){
                curSum = array[i];
            }else {
                curSum += array[i];
            }
            if (curSum > greatestSum)
                greatestSum = curSum;
        }

        return greatestSum;
    }*/

    //动态规划
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0)
            return 0;

        int res = array[0]; //记录当前所有子数组的和的最大值
        int max = array[0]; //包含array[i]的连续数组最大值
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max + array[i], array[i]);
            res = Math.max(max, res);
        }

        return res;
    }

    public static void main(String[] args) {
        N29 n29 = new N29();
        int[] array = {1, -2, 3, 10, -4, 7, 2, -5};
        int res = n29.FindGreatestSumOfSubArray(array);
        System.out.println(res);
    }
}
