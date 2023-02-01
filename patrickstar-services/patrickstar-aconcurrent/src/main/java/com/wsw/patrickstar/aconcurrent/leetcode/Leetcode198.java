package com.wsw.patrickstar.aconcurrent.leetcode;

/**
 * @Author WangSongWen
 * @Date: Created in 10:55 2021/3/16
 * @Description: 打家劫舍 dp
 */
public class Leetcode198 {
    // dp[i] = max(dp[i-2] + nums[i], dp[i-1])
    public int rob(int[] nums) {
        int pre = 0, next = 0, res = 0;
        for (int num : nums) {
            res = Math.max(pre + num, next);
            pre = next;
            next = res;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(new Leetcode198().rob(nums));
    }
}
