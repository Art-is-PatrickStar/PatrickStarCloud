package com.wsw.patrickstar.aconcurrent.leetcode.Array;

/**
 * 按奇偶排序数组
 *
 */
public class A1 {
    //使用额外数组
//    public int[] sortArrayByParity(int[] A) {
//
//        int[] temp = new int[A.length];
//
//        int begin = 0;
//        int end = A.length - 1;
//
//        for (int i = 0; i < A.length; i++) {
//            if (A[i] % 2 == 0){
//                temp[begin] = A[i];
//                begin++;
//            }
//            else{
//                temp[end] = A[i];
//                end--;
//            }
//        }
//
//        return temp;
//    }

    //不使用额外数组
    public int[] sortArrayByParity(int[] A) {
        int i = 0;
        int j = A.length - 1;

        while (i < j){
            if (A[i] % 2 == 1 && A[j] % 2 == 0) {
                int temp = A[j];
                A[j] = A[i];
                A[i] = temp;
            }
            if (A[i] % 2 == 0){
                i++;
            }
            if (A[j] % 2 == 1){
                j--;
            }
        }

        return A;
    }

    public static void main(String[] args) {
        A1 a = new A1();
        int[] test = {3,1,2,4,1};
        int[] res = a.sortArrayByParity(test);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }
}
