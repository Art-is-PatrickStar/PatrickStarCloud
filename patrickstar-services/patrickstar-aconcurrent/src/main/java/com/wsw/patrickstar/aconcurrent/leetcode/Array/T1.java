package com.wsw.patrickstar.aconcurrent.leetcode.Array;

public class T1
{
    public static void twoSum(int[] nums, int target)
    {
        /**
         * 返回指定数组元素相加结果的数组下标
         */
        for(int n=0; n<nums.length; n++)
        {
            for(int m=n+1; m<nums.length; m++)
            {
                if(nums[n] + nums[m] == target)
                    System.out.println("["+n+","+m+"]");
            }
        }
        //System.out.println("No sum solution!");

    }


    public static void main(String args[])
    {
//        int[] nums = new int[]{2, 7, 11, 15};
//        int target = 9;
        twoSum(new int[]{2, 7, 11, 15}, 18);

    }
}
