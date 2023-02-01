package com.wsw.patrickstar.aconcurrent.leetcode.Array;

/**
 * 删除数组中的重复元素
 *
 * 输出删除后数组的元素和数组长度
 *
 */
public class T2
{
    //双指针法
    public int removeDuplicates(int[] nums)
    {
        if (nums.length == 0)
            return 0;

        int i = 0;

        for(int j = 1; j < nums.length; j++)
        {
            /**
             * 定义j循环nums数组，当nums[j] != nums[i]的时候，让i++，这样就将i移动到了nums[j] = nums[i]的位置，
             * 这个时候将nums[j]赋值给nums[i]，就将重复的数删掉（覆盖）了。
             */
            if (nums[j] != nums[i])
            {
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;
    }

    public static void main(String[] args)
    {
        T2 t = new T2();

        int[] nums = new int[]{0,0,1,1,2};

        int len = t.removeDuplicates(nums);

        System.out.println(len);

        for (int i = 0; i < len; i++)
        {
            System.out.print(nums[i]+" ");
        }
    }

}
