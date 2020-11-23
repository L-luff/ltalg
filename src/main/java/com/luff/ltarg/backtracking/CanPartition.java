package com.luff.ltarg.backtracking;

import java.util.Arrays;

/**
 * @author lsq
 * @date 2020/10/11
 */
public class CanPartition {


    public static void main(String[] args) {
        int[] nums=new int[]{2,2,1,1};
        System.out.println(new CanPartition().canPartition(nums));
    }

    public boolean canPartition(int[] nums) {
        return solution1(nums);
    }


    // 递归，超出时间限制
    public boolean solution1(int[] nums){
        int sum=0;
        for(int num:nums){
            sum+=num;
        }
        if(sum % 2 != 0) return false;
        Arrays.sort(nums);
        int target=sum/2;
        return recursive(nums,target,0);
    }
    private boolean recursive(int[] nums,int target,int idx){
        if(target==0) return true;
        if(target<0) return false;
        if(idx>=nums.length) return false;
        if(nums[idx]>target) return false;
        boolean r=recursive(nums,target-nums[idx],idx+1);
        if(r) return true;
        return recursive(nums,target,idx+1);
    }



    //===================================
    // 动态规划
    public boolean solution2(int[] nums){
        int sum=0,max=0;
        for(int num:nums){
            sum+=num;
            max=Math.max(max,num);
        }
        int target=sum/2;
        if(sum % 2 != 0) return false;
        // 如果数组最大值大于平均值，那么剩下来的元素的所有和必定小于平均值。因此不可能平均分成两个子集
        if(max > target) return false;
        if(max==target) return true;
       // Arrays.sort(nums);

        // dp[i][j] 在0-i中选取某些元素使得和能否等于j ，0-i中可以什么都不选
        boolean[][] dp=new boolean[nums.length][target+1];

        dp[0][nums[0]]=true;
        for (int i=0;i<nums.length;i++){
            dp[i][0]=true; // 什么都不选的情况下，和肯定为0
        }

        for (int i=1;i<nums.length;i++){
            for (int j=1;j<=target;j++){
                dp[i][j]=dp[i-1][j];
                if (nums[i]<=j){
                   dp[i][j] = dp[i][j] || dp[i-1][j-nums[i]];
                }
            }
        }
        return  dp[nums.length-1][target];
    }



    // 动态规划，空间优化
    public boolean solution3(int[] nums){
        int sum=0,max=0;
        for(int num:nums){
            sum+=num;
            max=Math.max(max,num);
        }
        int target=sum/2;
        if(sum % 2 != 0) return false;
        // 如果数组最大值大于平均值，那么剩下来的元素的所有和必定小于平均值。因此不可能平均分成两个子集
        if(max > target) return false;
        if(max==target) return true;

        boolean[] dp=new boolean[target+1];
        dp[0]=true;
        for (int i=0;i<nums.length;i++){
            int val=nums[i];
            for (int j=target;j>=val;j--){
                dp[j]=dp[j] || dp[j-val];
            }
        }
        return dp[target];
    }
}
