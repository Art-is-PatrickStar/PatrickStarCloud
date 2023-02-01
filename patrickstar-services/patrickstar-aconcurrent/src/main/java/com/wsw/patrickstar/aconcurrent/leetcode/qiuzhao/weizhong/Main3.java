package com.wsw.patrickstar.aconcurrent.leetcode.qiuzhao.weizhong;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by wsw on 2019/9/19 17:48
 * 图中未经过的节点
 */
public class Main3 {
    static int n, m;
    static int[][] map = new int[101][101];
    static TreeSet<Integer> res = new TreeSet<>();
    static int[] flag = new int[101];
    static int[] tmp = new int[101];

    private static void run() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        for (int i = 1; i < n + 1; ++i) {
            Arrays.fill(map[i], 0);
            res.add(i);
            flag[i] = 0;
            tmp[i] = 0;
        }
        for (int i = 0; i < m; ++i) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            map[u][v] = 1;
            map[v][u] = 1;
        }
        int s = scanner.nextInt();
        int t = scanner.nextInt();
        res.remove(s);
        res.remove(t);
        flag[s] = 1;
        dfs(s, t);
        for (Integer r : res)
            System.out.print(r + " ");
        System.out.println();
        scanner.close();
    }

    private static void dfs(int s, int t) {
        if (t == s || tmp[s] == 1) {
            for (int i = 1; i <= n; ++i)
                if (flag[i] == 1)
                    res.remove(i);
            return;
        }
        for (int i = 1; i <= n; ++i) {
            if (flag[i] == 0 && map[s][i] == 1) {
                flag[i] = 1;
                dfs(i, t);
                flag[i] = 0;
            }
        }
        tmp[s] = 1;
    }

    public static void main(String[] args) {
        run();
    }
}
