package com.wsw.patrickstar.aconcurrent.leetcode.qiuzhao;

import java.util.Arrays;

/**
 * Created by wsw on 2019/9/16 08:22
 * 对公司员工年龄从小到大排序
 */
public class AgeSort {
    public void ageSort(int[] ages){
        int length = ages.length;
        int youngAge = 20;
        int oldAge = 80;
        int[] tempAges = new int[oldAge + 1];

        for (int i = 0; i < length; i++) {
            int j = ages[i];
            tempAges[j]++;
        }

        int index = 0;
        for (int i = youngAge; i <= oldAge; i++) {
            for (int j = 0; j < tempAges[i]; j++) {
                ages[index] = i;
                index++;
            }
        }
    }

    public static void main(String[] args) {
        int[] ages = {30, 25, 28, 40, 67, 21, 21, 59, 64};
        System.out.println(Arrays.toString(ages));
        AgeSort ageSort = new AgeSort();
        ageSort.ageSort(ages);
        System.out.println(Arrays.toString(ages));
    }
}
