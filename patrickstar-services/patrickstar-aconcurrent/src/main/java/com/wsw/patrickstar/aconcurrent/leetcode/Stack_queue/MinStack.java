package com.wsw.patrickstar.aconcurrent.leetcode.Stack_queue;

public class MinStack {
    /**
     * Your Stack_queue.MinStack object will be instantiated and called as such:
     * Stack_queue.MinStack obj = new Stack_queue.MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
    private int MaxSize = 100;
    private int[] nums;
    private int index = 0;
    private int add = 10;

    public MinStack() {
        nums = new int[MaxSize];
    }

    public void push(int x) {
        if (index < MaxSize){
            nums[index++] = x;
        }
        else {
            int[] newNums = new int[MaxSize + add];
            for (int i = 0; i < MaxSize; i++) {
                newNums[i] = nums[i];
            }
            nums = newNums;
            MaxSize += add;
            nums[index++] = x;
        }
    }

    public void pop() {
        if(index > 0)
            index--;
    }

    public int top() {
        if(index > 0) {
            int t = index - 1;
            return nums[t];
        }

        return 0;
    }

    public int getMin() {
        if (index == 0)
            return 0;

        int min = nums[0];
        for (int i = 0; i < index; i++) {
            if (nums[i] < min)
                min = nums[i];
        }

        return min;
    }

    public static void main(String[] args) {
        MinStack m = new MinStack();
        m.push(1);
        m.push(2);
        m.push(3);
        m.push(4);
        m.pop();
        int i = m.top();
        int j = m.getMin();
        System.out.println(i);
        System.out.println(j);
    }

}


