package com.wsw.patrickstar.aconcurrent.leetcode.Search;

/**
 * 寻找旋转排序数组中的最小值II
 * 二分查找
 */
public class S6
{
//    public int findMin(int[] nums)
//    {
//        int left = 0;
//        int right = nums.length - 1;
//        while (left <= right)
//        {
//            int mid = left + (right - left) / 2;
//            if (nums[mid] > nums[right])
//                left = mid + 1;
//            else if (nums[mid] < nums[left])
//                right = mid;
//            else
//                right--;
//        }
//        return nums[left];
//    }
    public int findMin(int[] nums)
    {
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return nums[0] < nums[1] ? nums[0] : nums[1];
        if (nums[0] < nums[nums.length - 1])
            return nums[0];

        int left = 0;
        int right = nums.length - 1;
        int min = nums[0];

        while (left <= right)
        {
            int mid = left + (right - left) / 2;
            if (mid >= 1 && (mid + 1) < nums.length && nums[mid] < nums[mid + 1] && nums[mid] < nums[mid - 1])
                return nums[mid];
            else if (left >= 1 && (left + 1) < nums.length && nums[left] < nums[left - 1] && nums[left] < nums[left + 1])
                return nums[left];
            else if (right >= 1 && (right + 1) < nums.length && nums[right] < nums[right - 1] && nums[right] < nums[right + 1])
                return nums[right];
            else if (nums[mid] > nums[0])
            {
                if (nums[mid] < min)
                    min = nums[mid];
                left = mid + 1;
            }
            else if (nums[mid] < nums[0])
            {
                if (nums[mid] < min)
                    min = nums[mid];
                right = mid - 1;
            }
            else
            {
                if (nums[mid] < min)
                    min = nums[mid];
                left++;
            }
        }

        if (min > nums[nums.length - 1])
            min = nums[nums.length - 1];
        if (min > nums[1])
            min = nums[1];
        if (min > nums[nums.length - 2])
            min = nums[nums.length - 2];

        return min;
    }

    public static void main(String[] args)
    {
        S6 s = new S6();
        int[] res1 = new int[]{1,3,5};
        int[] res2 = new int[]{2,0,0,0,1,2};
        int[] res3 = new int[]{2,2,2,0,1};
        System.out.println(s.findMin(res1));
        System.out.println(s.findMin(res2));
        System.out.println(s.findMin(res3));
    }
}
