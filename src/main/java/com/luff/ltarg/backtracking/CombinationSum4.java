package com.luff.ltarg.backtracking;

import java.util.Arrays;
import java.util.List;

/**
 * @author lsq
 * @date 2020/9/11
 * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 *
 * 示例:
 *
 * nums = [1, 2, 3]
 * target = 4
 *
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 *
 * 请注意，顺序不同的序列被视作不同的组合。
 *
 * 因此输出为 7。
 * 进阶：
 * 如果给定的数组中含有负数会怎么样？
 * 问题会产生什么变化？
 * 我们需要在题目中添加什么限制来允许负数的出现？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-iv
 *
 * @see com.luff.ltarg.dp.Change
 */
public class CombinationSum4 {

    public static void main(String[] args) {
        int[] nums=new int[]{1,2,3};
        int target=4;
        System.out.println(new CombinationSum4().solution2(nums,target));
    }
    public int combinationSum4(int[] nums, int target) {
       return solution1(nums,target);
    }

    // 超时时间限制
    public int solution1(int[] nums,int target){
        Arrays.sort(nums);
        dfs(nums,target);
        return res;
    }
    int res=0;
    public void dfs(int[] nums,int target){
        if (target==0){
            res++;
            return ;
        }
        for (int i=0;i<nums.length;i++){
            if (nums[i]<=target){
                dfs(nums,target-nums[i]);
            }
        }
    }

    //=====================================================

    //使用动态规划，dp[i] 代表target=i时的可能组合个数
    public int solution2(int[] nums,int target){
        int[] f=new int[target+1];
        f[0]=1;
        for (int i=1;i<=target;i++){
            for (int num:nums){
                f[i]+= num<=i ? f[i-num] : 0;
            }
        }
        return f[target];
    }
}
