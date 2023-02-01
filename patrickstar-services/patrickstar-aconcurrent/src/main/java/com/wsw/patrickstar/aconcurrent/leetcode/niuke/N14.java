package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

import java.util.Stack;

/**
 * 包含min函数的栈
 *
 */
public class N14 {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        stack1.push(node);
        if (stack2.isEmpty() || stack2.peek() >= node)
            stack2.push(node);
        else
            stack2.push(stack2.peek());
    }

    public void pop() {
        stack1.pop();
        stack2.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int min() {
        return stack2.peek();
    }

    public static void main(String[] args) {

    }
}
