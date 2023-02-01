package com.wsw.patrickstar.aconcurrent.leetcode.Search;

/**
 * 寻找旋转排序数组中的最小值
 *  二分查找
 */
public class S3
{
    public int findMin(int[] nums)
    {
        if (nums.length == 1)
            return nums[0];
        if (nums[0] < nums[nums.length - 1])
            return nums[0];

        int left = 0;
        int right = nums.length - 1;

        while (left <= right)
        {
            int mid = left + (right - left) / 2;
            if ((mid + 1) < nums.length && nums[mid] < nums[mid + 1] && nums[mid] < nums[mid - 1])
                return nums[mid];
            else if (left >= 1 && (left + 1) < nums.length && nums[left] < nums[left - 1] && nums[left] < nums[left + 1])
                return nums[left];
            else if (right >= 1 && (right + 1) < nums.length && nums[right] < nums[right - 1] && nums[right] < nums[right + 1])
                return nums[right];
            else if (nums[mid] > nums[0])
                left = mid + 1;
            else
                right = mid - 1;
        }

        return nums[0] < nums[nums.length - 1] ? nums[0] : nums[nums.length - 1];
    }

    public static void main(String[] args)
    {
        S3 s = new S3();
        int[] res1 = new int[]{3,4,5,1,2};
        int[] res2 = new int[]{4,5,6,7,0,1,2};
        int[] res3 = new int[]{2,1};
        System.out.println(s.findMin(res1));
        System.out.println(s.findMin(res2));
        System.out.println(s.findMin(res3));
    }
}
