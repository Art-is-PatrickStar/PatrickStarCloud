package com.wsw.patrickstar.aconcurrent.leetcode.Search;

public class S
{
    public static int search(int[] nums, int target)
    {
        int left = 0;
        int right = nums.length - 1;
        if (nums.length == 0)
            return -1;
        while (left <= right)
        {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args)
    {
        int res = search(new int[]{1,2,3,4,5,6}, 4);
        System.out.println(res);
    }
}
