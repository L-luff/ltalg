package com.luff.ltarg.array;

/**
 * @author: lsq
 */

public class MaxSubArray {


    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(maxSubArray(new int[]{5,4,-1,7,8}));
    }
    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        // 以index结尾的最大子数组和
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];
        for (int index=1;index < len; index++){
            dp[index] = Math.max(dp[index-1]+nums[index],nums[index]);
            max = Math.max(max,dp[index]);
        }
        return max;
    }
}
