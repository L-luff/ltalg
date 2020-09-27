package com.luff.ltarg.dp;

/**
 * @author lsq
 * @date 2020/7/30
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-break
 */
public class IntegerBreak {

    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }

    public static int integerBreak(int n) {

        return solution(n);

    }

    public static int solution(int n){

        // 令k为拆分出来的第一个证书，i-k 是剩下的整数，可以继续拆分，或者不再拆分
        int[] f=new int[n+1]; //

        for (int i=2;i<=n;i++){
            for (int j=1;j<i;j++){ // j为第一个才分出来的整数
                f[i]=Math.max(f[i],Math.max(j*(i-j),j * f[i-j]));
            }
        }
        return f[n];
    }
}
