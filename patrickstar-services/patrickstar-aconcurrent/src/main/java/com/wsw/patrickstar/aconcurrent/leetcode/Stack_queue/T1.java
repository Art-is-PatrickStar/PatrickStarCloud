package com.wsw.patrickstar.aconcurrent.leetcode.Stack_queue;

import java.util.Stack;

/**
 * 使括号有效的最少添加
 *
 */
public class T1 {
    public int minAddToMakeValid(String S) {
        Stack<Character> stack = new Stack<>();

        for (Character c : S.toCharArray()){
            if (c == '(')
                stack.push(c);
            else{
                if (!stack.isEmpty() && stack.peek() == '(')
                    stack.pop();
                else
                    stack.push(c);
            }
        }

        int res = 0;
        while (!stack.isEmpty()){
            stack.pop();
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        T1 t = new T1();
        System.out.println(t.minAddToMakeValid("())"));
        System.out.println(t.minAddToMakeValid("((("));
        System.out.println(t.minAddToMakeValid("((()"));
    }
}
