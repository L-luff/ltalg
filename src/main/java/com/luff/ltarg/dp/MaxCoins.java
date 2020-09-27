package com.luff.ltarg.dp;

import java.util.Arrays;

/**
 * @author lsq
 * @date 2020/7/19
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 * 说明:
 *
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 *
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/burst-balloons
 */
public class MaxCoins {

    public static void main(String[] args) {
        int[] nums=new int[]{3,1,5,8};
        System.out.println(maxCoins(nums));
    }
    public static int maxCoins(int[] nums) {
        return dpSolution(nums);
    }

    /**
     *将全过程看作是每次添加一个气球。
     * 我们定义方法 \textit{solve}solve，令 \textit{solve}(i,j)solve(i,j) 表示将开区间 (i,j)(i,j) 内的位置全部填满气球能够得到的最多硬币数。由于是开区间，因此区间两端的气球的编号就是 ii 和 jj，对应着 \textit{val}[i]val[i] 和 \textit{val}[j]val[j]。
     *
     * 当 i \geq j-1i≥j−1 时，开区间中没有气球，\textit{solve}(i,j)solve(i,j) 的值为 00；
     *
     * 当 i < j-1i<j−1 时，我们枚举开区间 (i,j)(i,j) 内的全部位置 \textit{mid}mid，令 \textit{mid}mid 为当前区间第一个添加的气球，该操作能得到的硬币数为 \textit{val}[i] \times \textit{val}[\textit{mid}] \times val[j]val[i]×val[mid]×val[j]。同时我们递归地计算分割出的两区间对 \textit{solve}(i,j)solve(i,j) 的贡献，这三项之和的最大值，即为 \textit{solve}(i,j)solve(i,j) 的值。这样问题就转化为求 \textit{solve}(i,\textit{mid})solve(i,mid) 和 \textit{solve}(\textit{mid},j)solve(mid,j) ，
     *
     * 递归
     * @param nums
     * @return
     */
    public static int solution1(int[] nums){
        int len=nums.length;
        int[] arr=new int[len+2];
        for (int i=1;i<=len;i++){
            arr[i]=nums[i-1];
        }
        arr[0]=1;
        arr[len+1]=1;
        // dp : 开区间(i,j)填充气泡所能获得的最大硬币。
        int[][] dp=new int[len+2][len+2];
        for (int i=0;i<dp.length;i++){
            Arrays.fill(dp[i],-1);
        }
        return recursive(dp,arr,0,len+1);
    }

    /**按照方法一的思路，我们发现我们可以通过变换计算顺序，从「自顶向下」的记忆化搜索变为「自底向上」的动态规划。
     * 令 dp[i][j]dp[i][j] 表示填满开区间 (i,j)(i,j) 能得到的最多硬币数，那么边界条件是 i \geq j-1i≥j−1，此时有 dp[i][j]=0dp[i][j]=0。
     * 基于上面，dp方法，自底向上
     * @param nums
     * @return
     */
    public static int dpSolution(int[] nums){
        int len=nums.length;
        int[] arr=new int[len+2];
        for (int i=1;i<=len;i++){
            arr[i]=nums[i-1];
        }
        arr[0]=arr[len+1]=1;
        int[][] dp=new int[len+2][len+2];
        for (int i=len-1;i>=0;i--){
            for (int j=i+2;j<=len+1;j++){
                for (int k=i+1;k<j;k++){
                    int max=arr[i] * arr[j] * arr[k];
                    max+=dp[i][k] + dp[k][j];
                    dp[i][j]=Math.max(dp[i][j],max);
                }
            }
        }
        return dp[0][len+1];
    }

    private static int recursive(int[][] dp,int[] arr,int left,int right){
        if (left>=right-1) return 0;

        if (dp[left][right]!=-1) return dp[left][right];

        for (int i=left+1;i<right;i++){
            int max=arr[left] * arr[right] * arr[i];
            max+=recursive(dp,arr,left,i) + recursive(dp,arr,i,right);
            dp[left][right]=Math.max(dp[left][right],max);
        }
        return dp[left][right];
    }
}
