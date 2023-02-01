package com.wsw.patrickstar.aconcurrent.leetcode.Stack_queue;

import java.util.Arrays;
import java.util.Stack;

/**
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高的天数。
 * 如果之后都不会升高，请输入 0 来代替。
 *
 */
public class T9 {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();

        int size = temperatures.length;
        int[] result = new int[size];
        Arrays.fill(result, 0);

        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                int t = stack.pop();
                result[t] = i - t;
            }

            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        T9 t9 = new T9();
        int[] result = t9.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});
        for (int i = 0; i < result.length; i++)
        {
            System.out.print(result[i] + " ");
        }
    }
}
