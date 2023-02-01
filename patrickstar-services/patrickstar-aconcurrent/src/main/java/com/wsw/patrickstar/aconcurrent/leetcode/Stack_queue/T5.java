package com.wsw.patrickstar.aconcurrent.leetcode.Stack_queue;

import java.util.Arrays;
import java.util.Stack;

/**
 * 栈
 * 下一个更大的元素2
 *
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，
 * 这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 */
public class T5 {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();

        int size = nums.length;
        int[] res = new int[size];
        Arrays.fill(res,-1);

        for (int i = 0; i < size * 2; i++) {
            int temp = nums[i % size];
            while (!stack.isEmpty() && nums[stack.peek()] < temp){
                res[stack.pop()] = temp;
            }
            if (i < size){
                stack.push(i);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        T5 t5 = new T5();
        int[] a = t5.nextGreaterElements(new int[]{100, 1, 11, 1, 120, 111});
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
