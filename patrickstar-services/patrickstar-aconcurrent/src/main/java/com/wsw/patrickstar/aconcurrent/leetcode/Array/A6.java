package com.wsw.patrickstar.aconcurrent.leetcode.Array;

/**
 * 删除排序数组中的重复元素II
 *
 */
public class A6 {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;

        if (nums.length == 1)
            return 1;

        int i = 0;
        int j = i;
        int count = 0;
        int tempCount = 0;

        while (i < nums.length){
            if (j < nums.length && nums[j] == nums[i]){
                j++;
                tempCount++;
            }
            else {
                if (tempCount >= 2){
                    count += 2;
                    nums[i+1] = nums[i];
                    i += 2;
                }
                else {
                    count++;
                    i++;
                }
                if (j >= nums.length)
                    break;
                nums[i] = nums[j];
                tempCount = 0;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        A6 a = new A6();
        int[] nums = {1,1,1,2,2,3};
        int res = a.removeDuplicates(nums);
        System.out.println(res);

        for (int i = 0; i < res; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
