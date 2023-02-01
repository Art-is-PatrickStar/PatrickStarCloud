package com.wsw.patrickstar.aconcurrent.leetcode.qiuzhao.didi;

import java.util.Scanner;

/**
 * Created by wsw on 2019/9/19 19:47
 * 找出数组区间的最小值
 */
public class Main1 {
    public static void main(String[] args) {
        //读取数据与处理
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        //原始数组
        int[] nums = new int[n+1];
        for (int i = 1; i <= n; i++) {
            nums[i] = sc.nextInt();
        }
        //维护前n项的和
        int[] sum = new int[n+1];
        for (int i = 1; i <=n ; i++) {
            sum[i] = sum[i-1]+nums[i];
        }
        int ans = 0;
        //dp[i]存储的是到i为止，前面所有大于等于m的组合的最小
        int[] dp = new int[n+1];
        for (int i = m; i <=n ; i++) {
            dp[i] = Math.min(dp[i-1],sum[i-1]-sum[i-m])+nums[i];
            ans = Math.min(ans,dp[i]);
        }
        System.out.println(ans);
    }
}
