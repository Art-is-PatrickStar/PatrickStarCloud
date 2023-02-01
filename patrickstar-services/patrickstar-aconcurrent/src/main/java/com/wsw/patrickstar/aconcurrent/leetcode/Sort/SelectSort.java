package com.wsw.patrickstar.aconcurrent.leetcode.Sort;

/**
 * 选择排序
 * 选择排序的基本思想：
 * 每一趟在n-i+1（i=1,2,3…,n-1）个记录中选取关键字最小的记录与第i个记录交换，并作为有序序列中的第i个记录。
 *
 * 待排序列：43，65，4，23，6，98，2，65，7，79
 * 第一趟： 2，65，4，23，6，98，43，65，7，79
 * 第二趟： 2，4，65，23，6，98，43，65，7，79
 * 第三趟： 2，4，6，23，65，98，43，65，7，79
 * 第四趟： 2，4，6，7，43，65，98，65，23，79
 * 第五趟： 2，4，6，7，23，65，98，65，43，79
 * 第六趟： 2，4，6，7，23，43，98，65，65，79
 * 第七趟： 2，4，6，7，23，43，65，98，65，79
 * 第八趟： 2，4，6，7，23，43，65，65，98，79
 * 第九趟： 2，4，6，7，23，43，65，65，79，98
 */
public class SelectSort {
    public static void Select_Sort(int[] nums) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int k = i;
            for (int j = i + 1; j < size; j++) {
                if (nums[j] < nums[k]){
                    k = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[k];
            nums[k] = temp;
        }
    }

    public static void main(String[] args) {
        int[] res = new int[]{43, 65, 4, 23, 6, 98, 2, 65, 7, 79};
        Select_Sort(res);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }
}