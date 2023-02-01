package com.wsw.patrickstar.aconcurrent.leetcode.Stack_queue;

import java.util.Stack;

public class T3
{
    /**
     * 栈
     *
     * 判断括号是否匹配
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(Character c : s.toCharArray()){
            if (c == '('){
                stack.push(')');
            }
            else if (c == '{'){
                stack.push('}');
            }
            else if (c == '['){
                stack.push(']');
            }
            else if (stack.isEmpty() || stack.pop() != c){
                return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        T3 t = new T3();
        System.out.println(t.isValid("()"));
        System.out.println(t.isValid("()[]"));
        System.out.println(t.isValid(")("));
        System.out.println(t.isValid("(){][]"));
    }
}
