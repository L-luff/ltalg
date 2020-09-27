package com.luff.ltarg.dp;

import java.util.Arrays;

/**
 * @author lsq
 * @date 2020/7/23
 *
给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例:

输入:
[
[1,3,1],
[1,5,1],
[4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class MinPathSum {


    public static void main(String[] args) {
        int[][] grid=new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1},
        };
        System.out.println(minPathSum(grid));
    }
    public static int minPathSum(int[][] grid) {
        return solution(grid);
    }


    // 滚动数组，动态规划
    public static int solution(int[][] grid){
        int row=grid.length,column=grid[0].length;
        int[] f=new int[column];
        Arrays.fill(f,Integer.MAX_VALUE);
        f[0]=grid[0][0];
        for (int i=0;i<row;i++){
            for (int j=0;j<column;j++){
                if (i==0 && j==0) continue;
                if (i==0 || j==0){
                    if(i!=0) {
                        f[j] = f[j] + grid[i][j];
                    }else{
                        f[j]=f[j-1]+grid[i][j];
                    }
                    continue;
                }
                f[j]=Math.min(f[j]+grid[i][j],f[j-1]+grid[i][j]);
            }
        }
        return f[column-1];
    }
}
