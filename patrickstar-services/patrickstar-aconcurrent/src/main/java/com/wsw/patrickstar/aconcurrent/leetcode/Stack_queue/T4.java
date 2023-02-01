package com.wsw.patrickstar.aconcurrent.leetcode.Stack_queue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 栈
 *
 * 给定两个没有重复元素的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
 * 找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 *
 */
public class T4
{
    public int[] nextGreaterElement(int[] nums1, int[] nums2)
    {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (Integer nums : nums2) {
            while (!stack.isEmpty() && stack.peek() < nums){
                hashMap.put(stack.pop(), nums);
            }
            stack.push(nums);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = hashMap.getOrDefault(nums1[i], -1);
        }

        return result;
    }

    public static void main(String[] args)
    {
        T4 t = new T4();
        int[] a = t.nextGreaterElement(new int[]{1,3,5,2,4}, new int[]{6,5,4,3,2,1,7});
        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
    }

}
