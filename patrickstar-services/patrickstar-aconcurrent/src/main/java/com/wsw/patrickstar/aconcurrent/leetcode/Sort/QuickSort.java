package com.wsw.patrickstar.aconcurrent.leetcode.Sort;

/**
 * 快速排序
 * 通过一趟排序将待排记录分隔成独立的两部分，
 * 其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 *
 */
public class QuickSort {

    public static void Quick_Sort(int[] nums) {
        if (nums.length > 0)
            Quick(nums, 0, nums.length - 1);
    }

    //递归排序
    private static void Quick(int[] nums, int low, int high) {
        if (low < high){
            int mid = getMiddle(nums, low, high);
            Quick(nums, low, mid - 1);
            Quick(nums, mid + 1, high);
        }
    }

    //找中间值
    public static int getMiddle(int[] nums, int low, int high) {
        int temp = nums[low];
        while (low < high){
            while (low < high && nums[high] >= temp){
                high--;
            }
            nums[low] = nums[high];
            while (low < high && nums[low] <= temp){
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = temp;
        return low;
    }

    public static void main(String[] args) {
        int[] res = new int[]{43, 65, 4, 23, 6, 98, 2, 65, 7, 79};
        Quick_Sort(res);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }
}
