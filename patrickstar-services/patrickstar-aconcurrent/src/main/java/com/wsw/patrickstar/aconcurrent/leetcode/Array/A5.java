package com.wsw.patrickstar.aconcurrent.leetcode.Array;

/**
 * 删除排序数组中的重复元素
 *
 */
public class A5 {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;

        if (nums.length == 1)
            return 1;

        int i = 0;
        for (int j = 1; j < nums.length; j++)
        {
            if (nums[j] != nums[i])
            {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        A5 a = new A5();
        int[] nums = {1,1,1,2,2,3};
        int res = a.removeDuplicates(nums);
        System.out.println(res);

        for (int i = 0; i < res; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
