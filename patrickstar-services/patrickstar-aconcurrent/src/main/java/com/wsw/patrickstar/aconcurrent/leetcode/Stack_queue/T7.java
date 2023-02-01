package com.wsw.patrickstar.aconcurrent.leetcode.Stack_queue;

import java.util.Stack;

/**
 * 棒球比赛
 *
 */
public class T7 {
    /**
     * 给定一个字符串列表，每个字符串可以是以下四种类型之一：
     * 1.整数（一轮的得分）：直接表示您在本轮中获得的积分数。
     * 2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
     * 3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
     * 4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
     *
     * @param ops
     * @return
     */
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < ops.length; i++){
            if (ops[i].equals("+") || ops[i].equals("C") || ops[i].equals("D")){
                if (ops[i].equals("+")){
                    int num1 = stack.pop();
                    int num2 = stack.pop();
                    stack.push(num2);
                    stack.push(num1);
                    stack.push(num1 + num2);
                }
                else if (ops[i].equals("C")){
                    stack.pop();
                }
                else if (ops[i].equals("D")){
                    int num1 = stack.pop();
                    stack.push(num1);
                    stack.push(num1 * 2);
                }
            }
            else {
                stack.push(Integer.parseInt(ops[i]));
            }
        }

        int sum = 0;
        while (!stack.isEmpty()){
            sum += stack.pop();
        }

        return sum;
    }

    public static void main(String[] args) {
        T7 t = new T7();
        int end = t.calPoints(new String[]{"5","-2","4","C","D","9","+","+"});
        System.out.println(end);
    }
}
