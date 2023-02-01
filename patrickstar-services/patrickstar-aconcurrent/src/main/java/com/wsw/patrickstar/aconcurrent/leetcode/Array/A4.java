package com.wsw.patrickstar.aconcurrent.leetcode.Array;

/**
 * 双指针
 * 移除元素
 */
public class A4 {
    public int removeElement(int[] nums, int val) {
        int count = 0;
        int begin = 0;
        int end = nums.length - 1;

        while (begin <= end){
            while (begin < nums.length && nums[begin] != val){
                begin++;
            }
            while (end >= 0 && nums[end] == val){
                count++;
                end--;
            }
            if (begin >= nums.length || begin >= end || end < 0){
                break;
            }
            int temp = nums[begin];
            nums[begin] = nums[end];
            nums[end] = temp;
        }

        return nums.length - count;
    }

    public static void main(String[] args) {
        A4 a = new A4();
        int[] nums = {0,1,2,2,3,0,4,2};
        int res = a.removeElement(nums, 2);
        System.out.println(res);

        for (int i = 0; i < res; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
