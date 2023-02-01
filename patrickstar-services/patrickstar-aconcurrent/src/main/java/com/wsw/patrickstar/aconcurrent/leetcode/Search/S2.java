package com.wsw.patrickstar.aconcurrent.leetcode.Search;

/**
 * 搜索旋转排序数组 II(二分查找)
 *
 */
public class S2
{
    public boolean search(int[] nums, int target)
    {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right)
        {
            int mid = left + (right - left) / 2;
            if (nums[left] == target || nums[mid] == target || nums[right] == target)
                return true;
            else if (nums[mid] > nums[left])
            {
                if (target > nums[mid])
                    left = mid + 1;
                else
                {
                    if (target > nums[left])
                        right = mid - 1;
                    else
                        left = mid + 1;
                }
            }
            else if (nums[mid] < nums[left])
            {
                if (target < nums[mid])
                    right = mid - 1;
                else
                {
                    if (target > nums[left])
                        right = mid - 1;
                    else
                        left = mid + 1;
                }
            }
            else
                left++; //当这二者相等的时候，无法讨论，所以只能是一次移动一个去逼近target
        }
        return false;
    }

    public static void main(String[] args)
    {
        S2 s = new S2();
        System.out.println(s.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 5));
        System.out.println(s.search(new int[]{1, 1, 3, 1}, 3));
        System.out.println(s.search(new int[]{1, 1, 3, 1}, 2));
    }
}
