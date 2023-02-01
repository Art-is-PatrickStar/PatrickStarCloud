package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 * 矩阵中的路径
 *
 * 回溯法
 *
 */
public class N3 {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || matrix.length != rows * cols || str == null)
            return false;

        boolean[] visited = new boolean[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPathCore(matrix, rows, cols, i, j, 0, str, visited))
                    return true;
            }
        }

        return false;
    }

    private boolean hasPathCore(char[] matrix, int rows, int cols, int i, int j, int index, char[] str, boolean[] visited){
        if (index == str.length)
            return true;

        if (!match(matrix, rows, cols, i, j, str[index]) || visited[i * cols + j] == true)
            return false;

        visited[i * cols + j] = true;
        boolean res = hasPathCore(matrix, rows, cols, i+1, j, index+1, str, visited) ||
                hasPathCore(matrix, rows, cols, i-1, j, index+1, str, visited) ||
                hasPathCore(matrix, rows, cols, i, j+1, index+1, str, visited) ||
                hasPathCore(matrix, rows, cols, i, j-1, index+1, str, visited);
        visited[i * cols + j] = false;

        return res;
    }

    private boolean match(char[] matrix, int rows, int cols, int i, int j, char c){
        if (i < 0 || i > rows - 1 || j < 0 || j > cols - 1)
            return false;
        return matrix[i * cols + j] == c;
    }

    public static void main(String[] args) {
        N3 n3 = new N3();
        String s = "abtgcfcsjdeh";
        String s1 = "bfce";
        String s2 = "abfb";
        System.out.println(n3.hasPath(s.toCharArray(), 3, 4, s1.toCharArray()));
        System.out.println(n3.hasPath(s.toCharArray(), 3, 4, s2.toCharArray()));
    }
}
