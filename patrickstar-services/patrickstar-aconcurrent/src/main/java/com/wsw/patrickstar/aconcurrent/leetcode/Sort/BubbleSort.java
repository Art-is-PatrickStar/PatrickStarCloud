package com.wsw.patrickstar.aconcurrent.leetcode.Sort;

/**
 * 冒泡排序
 * 冒泡排序的基本思想为：
 * 一趟冒泡排序的过程为：首先将第一个记录的关键字和第二个记录的关键字进行比较，若为逆序，则将两个记录交换之，
 * 然后比较第二个记录的关键字和第三个记录的关键字，依次类推，直至第n-1个记录和第n个记录的关键字进行过比较为止；
 *
 * 在冒泡排序的过程中，关键字较小的记录好比水中气泡逐趟向上漂浮，而关键字较大的记录好比石头往下沉，
 * 每一趟有一块“最大”的石头沉到水底。
 *
 * 例如：
 * 待排序列：43, 65, 4, 23, 6, 98, 2, 65, 7, 79
 * 第一趟： 43, 4，23，6，65，2，65，7，79，98
 * 第二趟： 4，23，6，43，2，65，7，65，79，98
 * 第三趟： 4，6，23，2，43，7，65，65，79，98
 * 第四趟： 4，6，2，23，7，43，65，65，79，98
 * 第五趟： 4，2，6，7，23，43，65，65，79，98
 * 第六趟： 2，4，6，7，23，43，65，65，79，98
 *
 */
public class BubbleSort {
    public static void Bubble_Sort(int[] nums) {
        int size = nums.length;
        int temp;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (nums[j] > nums[j + 1]){
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args)
    {
        int[] res = new int[]{43, 65, 4, 23, 6, 98, 2, 65, 7, 79};
        Bubble_Sort(res);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }
}
