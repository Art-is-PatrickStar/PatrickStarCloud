package com.wsw.patrickstar.aconcurrent.leetcode.Sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 两个数组的交集II
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 */
public class S2
{
    public int[] intersect(int[] nums1, int[] nums2)
    {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        ArrayList<Integer> list = new ArrayList();

        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length)
        {
            if (nums1[i] < nums2[j])
                i++;
            else if (nums1[i] > nums2[j])
                j++;
            else
            {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] nums = new int[list.size()];
        for (int k = 0; k < nums.length; k++)
        {
            nums[k] = list.get(k);
        }

        return nums;
    }

    public static void main(String[] args)
    {
        S2 s = new S2();
        int[] a = s.intersect(new int[]{4,9,5}, new int[]{9,4,9,8,4});
        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        int[] b = s.intersect(new int[]{1,2,2,1}, new int[]{2,2});
        for (int i = 0; i < b.length; i++)
        {
            System.out.print(b[i] + " ");
        }
    }
}
