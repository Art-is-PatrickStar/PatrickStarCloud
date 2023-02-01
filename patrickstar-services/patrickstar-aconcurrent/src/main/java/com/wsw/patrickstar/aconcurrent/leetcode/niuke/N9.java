package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 * 调整数组顺序使奇数位于偶数前面
 *
 */
public class N9 {
    //调整后的数组的两部分并没有保证有序
    /*public void reOrderArray(int [] array) {
        if (array == null || array.length == 0)
            return;

        int p1 = 0;
        int p2 = array.length - 1;

        while (p1 < p2){
            if (array[p1] % 2 == 0 && array[p2] % 2 == 1){
                int temp = array[p1];
                array[p1] = array[p2];
                array[p2] = temp;
            }

            if (array[p1] % 2 == 1)
                p1++;

            if (array[p2] % 2 == 0)
                p2--;
        }
    }*/

    //保证调整后的数组两部分是有序的
    //冒泡排序的思想
    public void reOrderArray(int [] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] % 2 == 0 && array[j + 1] % 2 == 1){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        N9 n9 = new N9();
        int[] a = new int[]{1,2,3,4,5};
        n9.reOrderArray(a);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
