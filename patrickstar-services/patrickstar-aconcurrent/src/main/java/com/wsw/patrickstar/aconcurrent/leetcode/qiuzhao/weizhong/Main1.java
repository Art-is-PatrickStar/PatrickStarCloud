package com.wsw.patrickstar.aconcurrent.leetcode.qiuzhao.weizhong;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Created by wsw on 2019/9/17 10:46
 * 正整数阶乘最后一位不为0的数
 */
public class Main1 {
    // 用BigDecimal
    public static void run(int num){
        BigDecimal big = new BigDecimal(1);
        for (int i = 1; i <= num; i++) {
            big = big.multiply(new BigDecimal(i));
        }
        String str = big.toString();
        System.out.println(str);
        for (int i = str.length()-1; i >= 0 ; i--) {
            if (str.charAt(i) != '0'){
                System.out.println(str.charAt(i));
                break;
            }
        }
    }

    // 在阶乘的过程中，如果遇到末尾有0的情况就把0去掉
    public static void run1(int num){
        long sum = 1;
        for (long i = 1; i <= num; i++) {
            sum *= i;
            while (0 == (sum % 10)){
                sum /= 10;
            }
            if (sum > 100000000){
                sum %= 100000000;
            }
        }
        if (sum == 0)
            sum = 0;
        System.out.println(sum % 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        run(num);
        //run1(num);
        scanner.close();
    }
}
