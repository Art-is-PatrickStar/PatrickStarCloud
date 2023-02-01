package com.wsw.patrickstar.aconcurrent.leetcode.qiuzhao.CVTE;

import java.util.Arrays;

/**
 * Created by wsw on 2019/9/15 09:52
 * 合并两个有序的数组
 */
public class MergeArray {
    public int[] merge(int[] A, int[] B){
        int lengthA = A.length, lengthB = B.length;
        int length = lengthA + lengthB;
        int[] C = new int[length];
        int i = 0, j = 0, k = 0;

        //将两个数组中大的数放入新的数组C
        while (i < lengthA && j < lengthB){
            if (A[i] > B[j]){
                C[k++] = A[i++];
            }
            else {
                C[k++] = B[j++];
            }
        }

        //将剩余的数放入新数组C中
        if (i >= lengthA){
            while (j < lengthB){
                C[k++] = B[j++];
            }
        }else {
            while (i < lengthA){
                C[k++] = A[i++];
            }
        }

        return C;
    }

    public static void main(String[] args) {
        MergeArray mergeArray = new MergeArray();
        int[] A = {98, 96, 90, 88};
        int[] B = {97, 95, 94, 82, 80};
        int[] C = mergeArray.merge(A, B);
        System.out.println(Arrays.toString(C));
    }
}
