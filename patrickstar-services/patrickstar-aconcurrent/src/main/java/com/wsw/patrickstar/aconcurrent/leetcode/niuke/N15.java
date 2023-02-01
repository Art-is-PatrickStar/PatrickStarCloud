package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

import java.util.Stack;

/**
 * 栈的压入、弹出序列
 *
 */
public class N15 {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA.length == 0 || popA.length == 0)
            return false;
        //借助一个辅助栈
        Stack<Integer> stack = new Stack<>();
        //记录出栈字符串的遍历下标
        int popIndex = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            //当栈顶元素等于出栈序列的字符，出栈,否则退出继续入栈
            while (!stack.isEmpty() && stack.peek() == popA[popIndex]){
                stack.pop();
                popIndex++;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        N15 n15 = new N15();
        int[] pushA = {1,2,3,4,5}, popA = {4,3,5,1,2};
        boolean res = n15.IsPopOrder(pushA, popA);
        System.out.println(res);
    }
}
