package com.wsw.patrickstar.aconcurrent.leetcode.qiuzhao.CVTE;

import java.util.Arrays;

/**
 * Created by wsw on 2019/9/15 11:01
 * 扑克牌顺子
 */
public class Puke {
    public boolean isContinuous(int [] numbers) {
        if (numbers == null || numbers.length == 0)
            return false;
        //0的个数
        int numOfZero = 0;
        //数组之间的间隔值
        int numOfInterval = 0;
        //排序
        Arrays.sort(numbers);

        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == 0){
                numOfZero++;
                continue;
            }
            //有对子直接返回false
            if (numbers[i] == numbers[i+1])
                return false;
            numOfInterval += numbers[i+1] - numbers[i] - 1;
        }

        return numOfZero >= numOfInterval;
    }

    public static void main(String[] args) {
        Puke puke = new Puke();
        int[] numbers = {1, 3, 2, 4, 6};
        boolean res = puke.isContinuous(numbers);
        System.out.println(res);
    }
}
