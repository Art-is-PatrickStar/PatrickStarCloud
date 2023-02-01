package com.wsw.patrickstar.aconcurrent.leetcode.Array;

/**
 * 旋转排序数组中的最小值
 * 利用二分查找
 */
public class A13 {
    public int minNumberInRotateArray(int [] nums) {
        if (nums == null || nums.length == 0)
            System.out.println("Error!");

        if (nums[0] < nums[nums.length - 1] || nums.length == 1)
            return nums[0];

        int start = 0;
        int end = nums.length - 1;
        int mid = 0;
        while (nums[start] >= nums[end]){
            if (end - start == 1){
                mid = end;
                break;
            }
            mid = (start + end) / 2;
            if (nums[start] == nums[end] && nums[end] == nums[mid])
                return minInorder(nums);
            if (nums[mid] >= nums[start])
                start = mid;
            else if (nums[mid] <= nums[end])
                end = mid;
        }

        return nums[mid];
    }

    //顺序查找
    private static int minInorder(int[] array){
        int result = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < result)
                result = array[i];
        }
        return result;
    }

    public static void main(String[] args) {
        A13 a13 = new A13();
        System.out.println(a13.minNumberInRotateArray(new int[]{3}));
        System.out.println(a13.minNumberInRotateArray(new int[]{3,4,5,1,2}));
        System.out.println(a13.minNumberInRotateArray(new int[]{1,2,3,4,5}));
        System.out.println(a13.minNumberInRotateArray(new int[]{1,0,1,1,1}));
    }
}
