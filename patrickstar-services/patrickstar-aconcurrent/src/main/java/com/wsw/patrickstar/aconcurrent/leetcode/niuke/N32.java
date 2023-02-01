package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

import java.util.Scanner;

/**
 * Created by wsw on 2019/9/14 17:19
 * 把数字翻译成字符串
 */
public class N32 {
    public int getTranslationCount(int number){
        if (number < 0)
            return 0;
        return translationCount(String.valueOf(number));
    }

    private int translationCount(String str) {
        int length = str.length();
        int[] countRecords = new int[length];
        //只有一个数字，则只有一种翻译方式
        countRecords[length - 1] = 1;
        int count;
        for (int i = length - 2; i >= 0; i--) {
            //f(i+1)
            count = countRecords[i + 1];
            int digit1 = str.charAt(i) - '0';
            int digit2 = str.charAt(i + 1) - '0';
            int connectedNumber = digit1 * 10 + digit2; //拼接两个数字
            //拼接的数字在10--25范围内
            if (connectedNumber >= 10 && connectedNumber <= 25){
                if (i < length - 2){
                    //f(i) = f(i+1) + f(i+2)
                    count += countRecords[i + 2];
                }else if (i == length - 2){
                    count += 1; //拼接的数字只有一种翻译方式
                }
            }
            countRecords[i] = count;
        }
        count = countRecords[0];
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        N32 n32 = new N32();
        int res = n32.getTranslationCount(num);
        System.out.println(res);
    }
}
