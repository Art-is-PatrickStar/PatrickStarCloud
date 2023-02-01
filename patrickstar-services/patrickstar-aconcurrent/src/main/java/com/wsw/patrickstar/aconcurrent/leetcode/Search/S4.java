package com.wsw.patrickstar.aconcurrent.leetcode.Search;

/**
 * 搜索插入位置
 * 二分查找
 */
public class S4
{
    public int searchInsert(int[] nums, int target)
    {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
        {
            if (target <= nums[0])
                return 0;
            else
                return 1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right)
        {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;

            if (left == right) //没有找到
            {
                if (nums[left] == target)
                    return left;
                break;
            }
        }
        if (nums[left] > target)
            return left;
        else
            return left + 1;
    }

    public static void main(String[] args)
    {
        S4 s = new S4();
        int a = s.searchInsert(new int[]{1,3,5,6},5);
        int b = s.searchInsert(new int[]{1,3,5,6},2);
        int c = s.searchInsert(new int[]{1,3,5,6},7);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
