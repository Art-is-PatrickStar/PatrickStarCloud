package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 * 表示数值的字符串
 *
 */
public class N8 {
    /*public boolean isNumeric(char[] str) {
        //正则表达式
        String s = String.valueOf(str);
        return s.matches("[+-]?[0-9]*(\\.[0-9]*)?([eE][+-]?[0-9]+)?");
    }*/

    public boolean isNumeric(char[] str) {
        int index = 0;

        if (str.length == 0)
            return false;

        if (str.length == 1 && (str[0] == '+' || str[0] == '-'))
            return false;

        if (str[0] == '+' || str[0] == '-') //跳过最开始的字符
            index++;

        index = judgeDigits(str, index);  //跳过前面的数字
        if (index == str.length) //正好遍历完，返回的是字符串的长度，返回true
            return true;

        //跳过数字后，遍历到小数点
        if (str[index] == '.'){
            index++;
            if (index == str.length)
                return false;
            index = judgeDigits(str, index);
            if (index == str.length)
                return true;
            if (str[index] == 'e' || str[index] == 'E'){
                index++;
                return judgeE(str, index);
            }
            return false;
        }
        else if (str[index] == 'e' || str[index] == 'E'){
            index++;
            return judgeE(str, index);
        }

        return false;
    }

    //判断是不是规范的带E的数字
    private boolean judgeE(char[] str, int index){
        if (index >= str.length)
            return false;

        if (str[index] == '+' || str[index] == '-')
            index++;

        //如果刚跳过e就到了字符串末尾，例如12e就是不规范的
        if (index >= str.length)
            return false;

        //跳过全部的数字
        index = judgeDigits(str, index);
        if (index == str.length)  //说明12e23这样的数字是规范的
            return true;

        return false;
    }

    private int judgeDigits(char[] str, int index){
        //判断是不是在0-9之间，不是的话就break返回下标值
        while (index < str.length){
            int number = str[index] - '0';
            if (number <= 9 && number >= 0)
                index++;
            else
                break;
        }

        return index;
    }

    public static void main(String[] args) {
        N8 n8 = new N8();
        System.out.println(n8.isNumeric(new char[]{'+','1','2','.','4','5','e','+','6'}));
        System.out.println(n8.isNumeric(new char[]{'1','a','3','.','1','4'}));
        System.out.println(n8.isNumeric(new char[]{'1','.','2','.','3'}));
    }
}
