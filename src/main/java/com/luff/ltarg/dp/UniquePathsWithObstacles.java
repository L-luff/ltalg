package com.luff.ltarg.dp;

/**
 * @author lsq
 * @date 2020/7/6
 *
 * number 63 medium
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *

 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class UniquePathsWithObstacles {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return solution2(obstacleGrid);
    }

    //dp question
    public static int solution1(int[][] obstacleGrid){
        int row=obstacleGrid.length,column=obstacleGrid[0].length;
        int[][] dp=new int[row][column];
        boolean obstacles=false;
        for (int i=0;i<column;i++){  //第0行数据最多只能有一种方法
            if (obstacleGrid[0][i]==1){
                obstacles=true;
            }
            if (!obstacles){
                dp[0][i]=1;
            }
        }
        obstacles=false;
        for (int i=0;i<row;i++){ // 第一列的所有行数据最多只有一种方法
            if (obstacleGrid[i][0]==1){
                obstacles=true;
            }
            if (!obstacles){
                dp[i][0]=1;
            }
        }
        for (int i=1;i<row;i++){
            for (int j=1;j<column;j++){
                dp[i][j]= obstacleGrid[i][j]==1 ? 0 : dp[i][j-1]+dp[i-1][j];
            }
        }

        return dp[row-1][column-1];
    }

    //dp question 时间复杂度不变，将空间复杂度O(mn) ---> O(n)
    // 滚动数组
    public static int solution2(int[][] obstacleGrid){
        int m=obstacleGrid.length,n=obstacleGrid[0].length;
        int[] dp=new int[n];

        dp[0]=obstacleGrid[0][0]==1?0:1;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (obstacleGrid[i][j]==1){
                    dp[j]=0;
                }else{
                    //&& obstacleGrid[i][j-1]==0 可以去掉
                    if(j-1>=0 && obstacleGrid[i][j-1]==0) {
                        dp[j] = dp[j] + dp[j - 1];
                    }
                }
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        int[][] ar=new int[][]{
                {1},
                {0}
        };
        System.out.println(uniquePathsWithObstacles(ar));
    }
}
