package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

import java.util.ArrayList;

/**
 * 顺时针打印一个矩阵
 *
 */
public class N13 {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        //可以把一个矩形分成很多个环形，可以发现每个环形的其实位置都是（i，i）
        ArrayList<Integer> aList = new ArrayList<>();
        if(matrix == null || matrix.length == 0){
            return aList;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;

        int start=0;

        while(true){
            int rowStart = start;
            int rowEnd = rows-start;
            int colStart = start;
            int colEnd = cols-start;
            if(rowStart>=rowEnd || colStart>=colEnd){
                break;
            }
            //接下来分别把一个环的四个部分添加到aList中
            for(int i = colStart;i<colEnd;i++){
                aList.add(matrix[rowStart][i]);
            }
            for(int i = rowStart+1;i<rowEnd;i++){
                aList.add(matrix[i][colEnd-1]);
            }
            if((rowEnd-rowStart )>1){
                for(int i = colEnd-2 ;i>=colStart;i--){
                    aList.add(matrix[rowEnd-1][i]);
                }
            }
            if((colEnd-colStart )>1){
                for(int i = rowEnd-2;i>rowStart;i--){
                    aList.add(matrix[i][colStart]);
                }
            }

            start++;
        }
        return aList;
    }

    public static void main(String[] args) {
        N13 n13 = new N13();
        int[][] matrix = {{1,2,3,4}, {5,6,7,8},{9,10,11,12},{13,14,15,16}};
        ArrayList<Integer> res = n13.printMatrix(matrix);
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i) + " ");
        }
    }
}
