package com.luff.ltarg.dp;

/**
 *
 */
public class MinCostClimbingStairs {

    public static int minCostClimbingStairs(int[] cost) {
        // todo another solution
        // 到达某个位置所有花费的体力
        if(cost==null || cost.length==1)
            return 0;
        if(cost.length==1)
            return cost[0];
        if(cost.length==2)
            return Math.min(cost[0],cost[1]);
        int[] dp=new int[cost.length];
        dp[0]=0;dp[1]=0;dp[2]=Math.min(cost[0],cost[1]);
        for (int i=3;i<cost.length;i++){
            dp[i]=Math.min(dp[i-2]+cost[i-2],dp[i-1]+cost[i-1]);
        }
        return Math.min(dp[cost.length-2]+cost[cost.length-2]
                            ,dp[cost.length-1]+cost[cost.length-1]);
    }

    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{1, 100}));
    }
}
