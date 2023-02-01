package com.wsw.patrickstar.aconcurrent.leetcode.Sort;

/**
 * 希尔排序
 * 将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序
 *
 */
public class ShellSort {
    public static void Shell_Sort(int[] nums) {
        int number = nums.length / 2; //增量
        int temp;
        int j;
        while (number >= 1){
            for (int i = number; i < nums.length; i++) {
                temp = nums[i];
                j = i - number;
                while (j >= 0 && nums[j] > temp){
                    nums[j + number] = nums[j];
                    j = j - number;
                }
                nums[j + number] = temp;
            }
            number = number / 2; //增量变为一半
        }
    }

    public static void main(String[] args) {
        int[] res = new int[]{43, 65, 4, 23, 6, 98, 2, 65, 7, 79};
        Shell_Sort(res);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }
}
