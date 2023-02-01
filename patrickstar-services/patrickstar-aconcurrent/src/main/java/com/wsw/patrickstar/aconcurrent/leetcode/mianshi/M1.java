package com.wsw.patrickstar.aconcurrent.leetcode.mianshi;

/**
 * 给你一个有序整数数组，数组中的数可以是正数、负数、零，请实现一个函数，这个函数返回一个整数：
 * 返回这个数组所有数的平方值中有多少种不同的取值
 */
public class M1 {

//    public int Handle(int[] nums){
//        if (nums.length == 0 || nums == null)
//            return 0;
//
//        HashSet<Integer> hashSet = new HashSet<>();
//
//        for (int n : nums) {
//            hashSet.add(Math.abs(n));  //绝对值相等就意味着平方相等
//        }
//
//        return hashSet.size();
//    }

    public int Handle(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int i = 0;
        int j = nums.length - 1;
        int res = 0;
        while (i <= j) {
            int num1 = Math.abs(nums[i]);
            int num2 = Math.abs(nums[j]);
            if (num1 < num2) {
                res++;
                while (i <= j && Math.abs(nums[j]) == num2)
                    j--;
            } else if (num1 > num2) {
                res++;
                while (i <= j && Math.abs(nums[i]) == num1)
                    i++;
            } else {
                res++;
                while (i <= j && Math.abs(nums[j]) == num2)
                    j--;
                while (i <= j && Math.abs(nums[i]) == num1)
                    i++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-3, 1, 1, 2, 3, 4, 4};
        M1 m = new M1();
        int res = m.Handle(nums);
        System.out.println(res);
    }
}
