package com.wsw.patrickstar.aconcurrent.leetcode.Sort;

/**
 * 插入排序
 * 每步将一个待排序的记录，按其顺序码大小插入到前面已经排序的字序列的合适位置（从后向前找到合适位置后），
 * 直到全部插入排序完为止。
 *
 */
public class InsertSort {
    public static void Insert_Sort(int[] nums) {
        int size = nums.length;
        int temp;
        int preIndex;
        for (int i = 1; i < size; i++) {
            preIndex = i - 1;
            temp = nums[i];
            while (preIndex >= 0 && temp < nums[preIndex]) {
                nums[preIndex + 1] = nums[preIndex];
                preIndex--;
            }
            nums[preIndex + 1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] res = new int[]{43, 65, 4, 23, 6, 98, 2, 65, 7, 79};
        Insert_Sort(res);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }
}
