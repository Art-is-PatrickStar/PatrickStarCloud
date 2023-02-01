package com.wsw.patrickstar.aconcurrent.leetcode.Search;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * 二分查找
 */
public class S5
{
    public int[] searchRange(int[] nums, int target)
    {
        int[] result = {-1, -1};
        result[0] = searchLeft(nums, target);
        if (result[0] != -1)
            result[1] = searchRight(nums, result[0], target);
        return result;
    }

    public int searchLeft(int[] nums, int target)
    {
        int left = 0;
        int right = nums.length - 1;
        int leftInit = -1;
        while (left <= right)
        {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target)
                right = mid - 1;
            else if (nums[mid] < target)
                left = mid + 1;
            else
            {
                leftInit = mid;
                right = mid - 1;
            }
        }
        return leftInit;
    }

    public int searchRight(int[] nums, int left, int target)
    {
        int right = nums.length - 1;
        int rightInit = -1;
        while (left <= right)
        {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target)
                right = mid - 1;
            else if (nums[mid] < target)
                left = mid + 1;
            else
            {
                rightInit = mid;
                left = mid + 1;
            }
        }
        return rightInit;
    }

    public static void main(String[] args)
    {
        S5 s = new S5();
        int[] a = s.searchRange(new int[]{5,7,7,8,8,10},8);
        int[] b = s.searchRange(new int[]{5,7,7,8,8,10},6);
        int[] c = s.searchRange(new int[]{4,5,7,7,8,8,10},5);
        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < b.length; i++)
        {
            System.out.print(b[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < c.length; i++)
        {
            System.out.print(c[i] + " ");
        }
    }
}
