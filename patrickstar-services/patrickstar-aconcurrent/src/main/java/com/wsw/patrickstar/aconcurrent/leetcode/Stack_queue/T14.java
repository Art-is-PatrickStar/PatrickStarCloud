package com.wsw.patrickstar.aconcurrent.leetcode.Stack_queue;

import java.util.Stack;

/**
 * 字符串解码
 *
 */
public class T14 {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();

        String res = "";
        int index = 0;
        int size = s.length();

        while (index < size){
            if (Character.isDigit(s.charAt(index))){
                int count = 0;
                while (Character.isDigit(s.charAt(index))){
                    count = count * 10 + (s.charAt(index) - '0');
                    index++;
                }
                countStack.push(count);
            }
            else if (s.charAt(index) == '['){
                resStack.push(res);
                res = "";
                index++;
            }
            else if (s.charAt(index) == ']'){
                String temp = resStack.pop();
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp += res;
                }
                res = temp;
                index++;
            }
            else {
                res += s.charAt(index++);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        T14 t = new T14();
        System.out.println(t.decodeString("3[a]2[bc]"));
        System.out.println(t.decodeString("3[a2[c]]"));
        System.out.println(t.decodeString("12[a2[bc]]"));
    }
}
