package com.wsw.patrickstar.aconcurrent.leetcode;

import java.util.Arrays;

/**
 * @Author WangSongWen
 * @Date: Created in 15:54 2021/2/25
 * @Description: 转置矩阵
 */
public class LeetCode867 {
    /**
     * @description:
     * @author: wangsongwen
     * @date: 2021/2/25 15:57
     * 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵
     * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引
     **/
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] resultMatrix = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                resultMatrix[j][i] = matrix[i][j];
            }
        }
        return resultMatrix;
    }

    public static void main(String[] args) {
        int[][] transpose = new LeetCode867().transpose(new int[][]{{1, 2, 3}, {4, 5, 6}});
        System.out.println(Arrays.deepToString(transpose));
    }
}
