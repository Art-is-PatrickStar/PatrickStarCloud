package com.wsw.patrickstar.aconcurrent.leetcode.Array;

/**
 * 二维数组中的查找
 */
public class A8 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0)
            return false;

        int rows = matrix.length - 1;
        int cols = matrix[0].length - 1;
        int i = 0;
        int j = cols;

        while (i <= rows && j >= 0){
            if (matrix[i][j] < target)
                i++;
            else if (matrix[i][j] > target)
                j--;
            else
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        A8 a8 = new A8();
        int[][] matrix = new int[][]{{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        System.out.println(a8.searchMatrix(matrix, 7));
        System.out.println(a8.searchMatrix(matrix, 5));
    }
}
