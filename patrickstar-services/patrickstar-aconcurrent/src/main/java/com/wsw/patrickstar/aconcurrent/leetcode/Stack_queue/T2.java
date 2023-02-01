package com.wsw.patrickstar.aconcurrent.leetcode.Stack_queue;

import java.util.Stack;

/**
 * 验证栈序列
 *
 */
public class T2 {
    public boolean validateStackSequences(int[] pushed, int[] popped){
        if (pushed.length != popped.length)
            return false;

        Stack<Integer> stack = new Stack<>();

        int j = 0;
        for (Integer i : pushed){
            stack.push(i);
            while (!stack.isEmpty() && stack.peek() == popped[j]){
                stack.pop();
                j++;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        T2 t = new T2();
        System.out.println(t.validateStackSequences(new int[]{1,2,3,4,5}, new int[]{5,4,3,2,1}));
        System.out.println(t.validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,3,5,1,2}));
        System.out.println(t.validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,3,5,2,1}));
    }
}
