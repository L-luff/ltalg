package com.luff.ltarg.dp;

/**
 * @author lsq
 * @date 2020/7/7
 */
public class MinSteps {


    public static void main(String[] args) {
        System.out.println(minSteps(12));
    }


    public static int minSteps(int n) {
        return solution(n);
    }


    public static int solution(int n){
        if (n<=1) return 0;
        int[] dp=new int[n+1];
        dp[2]=2;
        for (int i=3;i<=n;i++){
            int min=i;
            for (int j=i/2;j>=2;j--){
                if (i % j==0){
                    min=Math.min(min,dp[j]+i/j);
                }
            }
            dp[i]=min;
        }
        return dp[n];
    }

}
