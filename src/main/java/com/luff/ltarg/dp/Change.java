package com.luff.ltarg.dp;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lsq
 * @date 2020/9/11
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
 *
 *  
 *
 * 示例 1:
 *
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2:
 *
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * 示例 3:
 *
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 *  
 *
 * 注意:
 *
 * 你可以假设：
 *
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change-2
 * @see com.luff.ltarg.backtracking.CombinationSum4
 */
public class Change {

    public static void main(String[] args) throws Exception {
        int amount=5;
        int[] coins=new int[]{1,2,5};
        // System.out.println(new Change().solution2(amount,coins));
        System.out.println(new Change().dp(amount,coins));

    }

    //TODO
    public int change(int amount, int[] coins) {
        return solution1(amount, coins);
    }

    //1: 一般方法，回溯  超时
    public int solution1(int amount,int[] coins){
         bfs(coins,amount,0);
         return res;
    }

    int res=0;
    public void bfs(int[] coins,int target,int idx){
        if (target==0){
            res++;
            return ;
        }
        if (idx==coins.length) return ;

        // 对于当前位置idx的coin，要么使用，要么不适用 注意：当前答案需要的是组合，而不是排列。组合即：2,1,1和1,2,1是相同的答案
        // 不适用当前硬币的情况
        bfs(coins,target,idx+1);

        // 使用当前硬币的情况,需要判断coins[idx]是否小于等于target,如果大于，那么可能无法凑成给定顶金额，那么直接退出即可
        if(coins[idx]<=target){
            // 使用该硬币后，还可以继续使用
            bfs(coins,target-coins[idx],idx);
        }
    }
    //==============================

    //2: 使用自顶向下的方法，定义二维数组，在递归时，保存已经可能的组合数，
    // 超出时间限制
    public int solution2(int amount,int[] coins){
        f=new int[coins.length][amount+1];
        bfs2(coins,amount,0);
        return res;
    }

    int[][] f; //当前f[idx][target] 代表：当前idx，剩余金额为target时的可能的组合数
    public int bfs2(int[] coins,int target,int idx){
        if (target==0){
            res++;
            return 1;
        }
        if (idx==coins.length) return 0;
        if (f[idx][target]!=0){
            res+=f[idx][target];
            return f[idx][target];
        }
        int pre=0;
        pre+=bfs2(coins,target,idx+1);
        if (coins[idx]<=target){
            pre+=bfs2(coins,target-coins[idx],idx);
        }

        f[idx][target]=pre;
        return pre;
    }

    //========================================
    // 3：动态规划的方法
    // 定义intf[][] 使用前i个金币，那么前i个金币（有些金币可以不适用）形成的金额总额为amount的可能的组合数
    // 那么 f[i][amount]=f[i-1][amount](不适用第i个金额，那么可能的组合数就是前i-1个金币总额为amount的可能数)
    //      +f[i][amount-coins[i]] （使用第i个金额，那么需要加上 总额为amount-coins[i]时的可能组合数）

    public int dp(int amount,int[] coins){
        if (coins.length==0){
            if (amount==0){
                return 1;
            }
            return 0;
        }
        int[][] f=new int[coins.length+1][amount+1];
        for (int i=0;i<coins.length;i++){
            f[i+1][0]=1;
        }
        for (int i=1;i<=coins.length;i++){
            int coin=coins[i-1];
            for (int j=1;j<=amount;j++){
                f[i][j]=f[i-1][j];
                if (coin<=j){
                    f[i][j]+=f[i][j-coin];
                }
            }
        }
        return f[coins.length][amount];
    }


    // 第二种dp，
    public int dp2(int amount,int[] coins){
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int x = coin; x < amount + 1; ++x) {
                dp[x] += dp[x - coin];
            }
        }
        return dp[amount];

    }
}
