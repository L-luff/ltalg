package com.luff.ltarg.dp;

/**
 * @author lsq
 * @date 2020/7/10
 *
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]

 */
public class MaxProfit {


    public static void main(String[] args) {
        int[] prices=new int[]{1,2,3,0,2,9};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        return solution(prices);
    }
    //解法1
    public static int solution(int[] prices){
        if (prices.length<=1) return 0;
        int len=prices.length;
        int[] dp=new int[len];
        dp[0]=0;
        if (prices[1]>prices[0]){
            dp[1]=prices[1]-prices[0];
        }
        for (int i=2;i<len;i++){
            dp[i]=dp[i-1]; // 买入 or 冷冻期
            for (int j=i-1;j>=0;j--){
                dp[i]=Math.max(dp[i],j-2>=0?(dp[j-2]+(prices[i]-prices[j])):(prices[i]-prices[j]));
            }
        }
        return dp[len-1];
    }

    //解法2
    // 具体解法可看官方例子
    // https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/zui-jia-mai-mai-gu-piao-shi-ji-han-leng-dong-qi-4/
    public static int solution2(int[] prices){
        if (prices.length==0) return 0;

        int len=prices.length;
        /**
         * 分为3中状态 第 ii 天结束之后的「累计最大收益」
         * dp[i] 表示 第i天交易之后的状态
         * dp[i][0]: 第i天交易之后，持有股的状态
         * dp[i][1]: 第i天交易之后，没有持有股，并且处于冻结状态
         * dp[i][2]: 第i天交易之后，没有持有股，没有处于冻结状态
         */
        int[][] dp=new int[len][3];
        //第1天如果要持有股，那么第一天必须要买入
        //第1天除了持有股状态，其他状态值都是0
        dp[0][0]=-prices[0];
        for (int i=1;i<len;i++){
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][2]-prices[i]);
            dp[i][1]=dp[i-1][0]+prices[i];
            dp[i][2]=Math.max(dp[i-1][1],dp[i-1][2]);
        }

        return Math.max(dp[len-1][1],dp[len-1][2]);
    }
}
