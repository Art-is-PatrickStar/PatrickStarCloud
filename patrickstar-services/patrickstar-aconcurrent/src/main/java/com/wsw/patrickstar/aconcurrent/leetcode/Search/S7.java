package com.wsw.patrickstar.aconcurrent.leetcode.Search;

/**
 * 搜索二维数组
 *
 */
public class S7
{
    public boolean searchMatrix(int[][] matrix, int target)
    {
        if (matrix.length == 0)
            return false;
        int rowIndex = 0;
        int colIndex = matrix[0].length - 1;
        while (rowIndex < matrix.length && colIndex >= 0)
        {
            if (target == matrix[rowIndex][colIndex])
                return true;
            else if (target > matrix[rowIndex][colIndex])
                rowIndex++;
            else
                colIndex--;
        }
        return false;
    }
//    public boolean searchMatrix(int[][] matrix, int target)
//    {
//        if (matrix.length == 0)
//            return false;
//        int n = matrix[0].length;
//        int left = 0;
//        int right = matrix.length * matrix[0].length - 1;
//        while (left <= right)
//        {
//            int mid = left + (right - left) / 2;
//            if(target == matrix[mid/n][mid%n])
//                return true;
//            else if(target < matrix[mid/n][mid%n])
//                right = mid - 1;
//            else
//                left = mid + 1;
//        }
//        return false;
//    }

    public static void main(String[] args)
    {
        S7 s = new S7();
        boolean res1 = s.searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}}, 3);
        boolean res2 = s.searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}}, 13);
        System.out.println(res1);
        System.out.println(res2);
    }
}
