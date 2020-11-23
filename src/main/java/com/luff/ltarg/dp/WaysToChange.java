package com.luff.ltarg.dp;

import java.util.HashSet;
import java.util.Set;

/**
 * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
 *
 * 示例1:
 *
 *  输入: n = 5
 *  输出：2
 *  解释: 有两种方式可以凑成总金额:
 * 5=5
 * 5=1+1+1+1+1
 * 示例2:
 *
 *  输入: n = 10
 *  输出：4
 *  解释: 有四种方式可以凑成总金额:
 * 10=10
 * 10=5+5
 * 10=5+1+1+1+1+1
 * 10=1+1+1+1+1+1+1+1+1+1
 * 说明：
 *
 * 注意:
 *
 * 你可以假设：
 *
 * 0 <= n (总金额) <= 1000000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-lcci
 */
public class WaysToChange {

    public static void main(String[] args) {
        System.out.println(new WaysToChange().waysToChange(10));
        System.out.println(new WaysToChange().dp(10));
    }
    public int waysToChange(int n) {
        int[] coins=new int[]{1,5,10,25};
        recursive(n,coins,0,0);
        return ways;
    }

    int ways=0;
    //暴力回溯，超时
    public void recursive(int n,int[] coins,int idx,int amount){
        if(amount==n){
            ways++;
            return ;
        }
        if (amount>n){
            return ;
        }
        for (int i=idx;i<coins.length;i++){
            if (amount+coins[i]>n){
                return ;
            }
            recursive(n,coins,i,amount+coins[i]);
        }
    }

    public int dp(int n){
        // dp[i] 凑成总金额为i有dp[i]种方
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=1;
        int[] coins=new int[]{1,5,10,25};
        Set<Integer> marked=new HashSet<>();
        for (int i=2;i<=n;i++){
            marked.clear();
            for (int j=0;j<coins.length;j++){
                if (i>=coins[j]){
                    int coin=i-coins[j];
                    if (!marked.contains(coin)) {
                        dp[i] += dp[coin];
                    }
                    marked.add(coins[j]);
                }
            }
        }
        return dp[n];
    }


}
