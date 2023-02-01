package com.wsw.patrickstar.aconcurrent.leetcode.Sort;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 两个数组的交集I
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 */
public class S1
{
    public int[] intersection(int[] nums1, int[] nums2) 
    {
        Set<Integer> nums = new HashSet<>();
        Set<Integer> result = new HashSet<>();

        for (int i = 0; i < nums2.length; i++)
        {
            nums.add(nums2[i]); //去掉重复元素了
        }

        for (int i = 0; i < nums1.length; i++)
        {
            if (nums.contains(nums1[i]))
                result.add(nums1[i]);
        }

        int[] num3 = new int[result.size()];
        Iterator iterator = result.iterator();
        int i = 0;
        while (iterator.hasNext())
        {
            num3[i++] = (int)iterator.next(); //集合变为数组
        }

        return num3;
    }

    public static void main(String[] args)
    {
        S1 s = new S1();
        int num[] = s.intersection(new int[]{4,9,5}, new int[]{9,4,9,8,4});
        for (int i = 0; i < num.length; i++)
        {
            System.out.print(num[i] + " ");
        }
    }
}
