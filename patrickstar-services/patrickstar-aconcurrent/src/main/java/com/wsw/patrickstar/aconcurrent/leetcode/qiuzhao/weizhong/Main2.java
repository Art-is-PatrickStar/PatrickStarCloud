package com.wsw.patrickstar.aconcurrent.leetcode.qiuzhao.weizhong;

import java.util.Scanner;

/**
 * Created by wsw on 2019/9/19 16:57
 * 询问次数
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long num = scanner.nextInt();
        long sum = 1;
        for (long i = 1; i <= num ; i++) {
            if (sum > sum % 1000003){
                sum = sum % 1000003;
            }
            sum *= i;
        }
        System.out.println(sum % 1000003);
    }
}
