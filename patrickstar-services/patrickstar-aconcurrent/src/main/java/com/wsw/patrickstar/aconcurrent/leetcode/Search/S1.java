package com.wsw.patrickstar.aconcurrent.leetcode.Search;

/**
 * 搜索旋转排序数组(二分查找)
 *
 */
public class S1
{
    public int search(int[] nums, int target)
    {
        if (nums.length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right)
        {
            int mid = left + (right - left) / 2;
            if (nums[left] == target)
                return left;
            else if (nums[right] == target)
                return right;
            else if (nums[mid] == target)
                return mid;
            else
            {
                if (target > nums[mid])
                {
                    if (nums[mid] > nums[0])
                        left = mid + 1;
                    else
                    {
                        if (target > nums[0])
                            right = mid - 1;
                        else
                            left = mid + 1;
                    }
                }
                else
                {
                    if (nums[mid] < nums[0])
                        right = mid - 1;
                    else
                    {
                        if (target < nums[0])
                            left = mid + 1;
                        else
                            right = mid - 1;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args)
    {
        S1 s = new S1();
        int res = s.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 6);
        System.out.println(res);
    }
}
