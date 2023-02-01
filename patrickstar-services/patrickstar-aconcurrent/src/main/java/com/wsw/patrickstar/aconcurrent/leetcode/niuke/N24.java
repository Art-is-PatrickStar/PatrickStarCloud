package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 字符串的排列
 *
 */
public class N24 {
    private ArrayList<String> Permutation(String str) {
        ArrayList<String> resultList = new ArrayList<>();

        if (str == null)
            return resultList;

        doPermutation(str.toCharArray(), resultList, 0);
        Collections.sort(resultList);
        return resultList;
    }

    //递归
    private void doPermutation(char[] ch, ArrayList<String> resultList, int i) {
        //递归的终止条件
        if (i == ch.length - 1){
            //判断是否重复
            if (!resultList.contains(new String(ch))){
                resultList.add(new String(ch));
            }
        }else {
            //回溯法
            //递归的思想与栈的入栈和出栈是一样的,某一个状态遇到return结束了之后，会回到被调用的地方继续执行
            for (int j =i; j < ch.length; j++){
                swap(ch, i, j);
                doPermutation(ch, resultList, i+1);
                swap(ch, i, j);
            }
        }
    }

    //交换数组两个下标的元素
    private void swap(char[] ch, int i, int j) {
        if (i != j){
            char t = ch[i];
            ch[i] = ch[j];
            ch[j] = t;
        }
    }

    public static void main(String[] args) {
        N24 n24 = new N24();
        String s = "abc";
        ArrayList<String> resList = n24.Permutation(s);
        for (String value : resList) {
            System.out.println(value);
        }
    }
}
