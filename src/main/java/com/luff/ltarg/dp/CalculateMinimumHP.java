package com.luff.ltarg.dp;

import java.util.Arrays;

/**
 * @author lsq
 * @date 2020/7/12
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 *
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 *
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 *
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 *
 *  
 *
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 *
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 *
 * -2 (K)	-3	3
 * -5	-10	1
 * 10	30	-5 (P)
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dungeon-game
 * 说明:
 *
 * 骑士的健康点数没有上限。
 *
 * 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。

 */
public class CalculateMinimumHP {

    public static void main(String[] args) {
        System.out.println(calculateMinimumHP(new int[][]{{-1,-3,-4}}));
    }
    public static int calculateMinimumHP(int[][] dungeon) {
        return solution1(dungeon);
    }


    public static int solution1(int[][] dungeon){
        //dp[i][j] 表示i,j到终点所需要的最小初始化值
        int row=dungeon.length,column=dungeon[0].length;
        int[][] dp=new int[row+1][column+1];
        for(int i=0;i<=row;i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        dp[row][column-1]=dp[row-1][column]=1;
        for (int i=row-1;i>=0;i--){
            for (int j=column-1;j>=0;j--){
                int m=Math.min(dp[i+1][j],dp[i][j+1]);
                dp[i][j]=Math.max(m-dungeon[i][j],1);
            }
        }
        return dp[0][0];
    }
}
