package com.wsw.patrickstar.aconcurrent.leetcode.Stack_queue;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 函数的独占时间
 *
 */
public class T13 {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] times = new int[n];
        Stack<Integer> stack = new Stack<>();

        String[] s = logs.get(0).split(":");
        stack.push(Integer.parseInt(s[0]));

        int i = 1;
        int pre = Integer.parseInt(s[2]);
        while (i < logs.size()){
            s = logs.get(i).split(":");
            if (s[1].equals("start")){
                if (!stack.isEmpty()){
                    times[stack.peek()] += Integer.parseInt(s[2]) - pre;
                }
                stack.push(Integer.parseInt(s[0]));
                pre = Integer.parseInt(s[2]);
            }
            else {
                times[stack.peek()] += Integer.parseInt(s[2]) - pre + 1;
                stack.pop();
                pre = Integer.parseInt(s[2]) + 1;
            }
            i++;
        }

        return times;
    }

    public static void main(String[] args) {
        T13 t = new T13();
        List<String> list = new LinkedList<>();
        list.add("0:start:0");
        list.add("1:start:2");
        list.add("2:start:3");
        list.add("1:end:5");
        list.add("2:end:6");
        list.add("0:end:8");
        int[] res = t.exclusiveTime(3, list);
        for (Integer i : res){
            System.out.println(i + " ");
        }
    }
}
