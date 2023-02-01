package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 *  数组中出现次数超过一半的数字
 *  2019.8.26 wsw
 */
public class N25 {
   /* public int MoreThanHalfNum_Solution(int [] array) {
        if (array.length == 0)
            return 0;
        Arrays.sort(array);
        int mid = array[array.length / 2];
        int count = 0;
        for (int value : array) {
            if (value == mid)
                ++count;
        }
        return (count > array.length/2)? mid : 0;
    }*/

    public int MoreThanHalfNum_Solution(int [] array) {
        if (array.length == 0)
            return 0;
        int result = array[0];
        int times = 1;

        for (int i = 1; i < array.length; i++) {
            if (times == 0) {
                result = array[i];
                times = 1;
            } else if (array[i] == result) {
                ++times;
            } else {
                --times;
            }
        }

        times = 0;
        for (int value : array) {
            if (value == result)
                ++times;
        }

        return (times > array.length/2)? result: 0;
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,2,2,2,5,2,3};
        N25 n25 = new N25();
        int res = n25.MoreThanHalfNum_Solution(array);
        System.out.println(res);
    }
}
