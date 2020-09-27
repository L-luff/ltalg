package com.luff.ltarg.twoPointer;

/**
 * @author lsq
 * @date 2020/6/28
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 *
 * 示例: 
 *
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 进阶:
 *
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 */
public class MinSubArrayLen {

    public static void main(String[] args) {
        int res = minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
        System.out.println(res);
    }
    public static int minSubArrayLen(int s, int[] nums) {
        return solution1(s,nums);
    }


    // 双指针解决
    public static int solution1(int s,int[] nums){
        if(nums.length<=0) return 0;
        int start=0,end=0,len=nums.length,res=Integer.MAX_VALUE;
        int sum=0;
        while (start<=end && end <len){
            sum+=nums[end];
            while(sum >= s){
                res=Math.min(res,end-start+1);
                sum-=nums[start++];
            }
            end++;
        }
        return res==Integer.MAX_VALUE ? 0 : res;
    }
}
