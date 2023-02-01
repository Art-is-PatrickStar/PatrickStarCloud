package com.wsw.patrickstar.aconcurrent.leetcode.Array;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * 数组中重复的数据
 */
public class A3 {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new LinkedList<>();
        int n = nums.length;
        int[] temp = new int[n + 1];
        for (int i = 0; i < nums.length; i++) {
            temp[nums[i]] += 1;
        }
        for (int i = 0; i < temp.length; i++ ) {
            if (temp[i] > 1)
                list.add(i);
        }
        return list;
    }

    public static void main(String[] args) {
        A3 a = new A3();
        int[] nums = {4,3,2,7,8,2,3,1};
        List resList = a.findDuplicates(nums);
        for (int i = 0; i < resList.size(); i++) {
            System.out.print(resList.get(i) + " ");
        }
    }
}
