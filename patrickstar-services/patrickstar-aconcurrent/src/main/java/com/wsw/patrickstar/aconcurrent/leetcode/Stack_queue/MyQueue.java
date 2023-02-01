package com.wsw.patrickstar.aconcurrent.leetcode.Stack_queue;

import java.util.Stack;

/**
 * 用栈实现队列
 *
 */
public class MyQueue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    /** Initialize your data structure here. */
    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        stack2.push(x);
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack2.pop();
    }

    /** Get the front element. */
    public int peek() {
        return stack2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack2.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        myQueue.push(4);
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.peek());
        System.out.println(myQueue.empty());
    }
}
