package com.wsw.patrickstar.aconcurrent.leetcode.qiuzhao.didi;

import java.util.Scanner;

/**
 * Created by wsw on 2019/9/19 20:22
 * 垃圾分类
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println((n * (n - 1) / 2 - m) / 2);
    }
}
