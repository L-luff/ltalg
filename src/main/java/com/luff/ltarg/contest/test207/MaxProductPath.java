package com.luff.ltarg.contest.test207;

/**
 * @author lsq
 * @date 2020/9/20
 *给你一个大小为 rows x cols 的矩阵 grid 。最初，你位于左上角 (0, 0) ，每一步，你可以在矩阵中 向右 或 向下 移动。
 *
 * 在从左上角 (0, 0) 开始到右下角 (rows - 1, cols - 1) 结束的所有路径中，找出具有 最大非负积 的路径。路径的积是沿路径访问的单元格中所有整数的乘积。
 *
 * 返回 最大非负积 对 109 + 7 取余 的结果。如果最大积为负数，则返回 -1 。
 *
 * 注意，取余是在得到最大积之后执行的。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：grid = [[-1,-2,-3],
 *              [-2,-3,-3],
 *              [-3,-3,-2]]
 * 输出：-1
 * 解释：从 (0, 0) 到 (2, 2) 的路径中无法得到非负积，所以返回 -1
 * 示例 2：
 *
 * 输入：grid = [[1,-2,1],
 *              [1,-2,1],
 *              [3,-4,1]]
 * 输出：8
 * 解释：最大非负积对应的路径已经用粗体标出 (1 * 1 * -2 * -4 * 1 = 8)
 * 示例 3：
 *
 * 输入：grid = [[1, 3],
 *              [0,-4]]
 * 输出：0
 * 解释：最大非负积对应的路径已经用粗体标出 (1 * 0 * -4 = 0)
 * 示例 4：
 *
 * 输入：grid = [[ 1, 4,4,0],
 *              [-2, 0,0,1],
 *              [ 1,-1,1,1]]
 * 输出：2
 * 解释：最大非负积对应的路径已经用粗体标出 (1 * -2 * 1 * -1 * 1 * 1 = 2)
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-non-negative-product-in-a-matrix
 */
public class MaxProductPath {

    public static void main(String[] args) {
        int[][] grid=new int[][]{
                {1,-1,2,1,-1,0,0,4,3,2,0,-2,-2},
                {-2,3,3,-1,-1,0,0,-2,4,-3,3,0,0},
                {-4,-1,-1,-2,2,-1,-2,-2,0,3,-1,-4,1},
                {-3,4,-3,0,-3,1,-3,1,4,4,-4,-4,-2},
                {3,-3,1,0,-1,-4,-4,-4,3,2,2,3,3},
                {2,-1,-1,-4,-3,-3,4,2,3,4,4,-4,0},
                {4,-1,2,-3,-1,-1,-3,-4,4,4,4,-3,-1},
                {-3,-4,4,-2,-1,2,3,-1,2,3,4,4,-4},
                {-3,-1,-2,1,1,-1,-3,-4,-3,1,-3,3,-4},
                {2,4,4,4,-3,-3,1,-1,3,4,-1,1,4},
                {2,-2,0,4,-1,0,-2,4,-4,0,0,2,-3},
                {1,1,-3,0,-4,-4,-4,-4,0,-1,-4,-1,0},
                {3,-1,-3,-3,-3,-2,-1,4,-1,-2,4,2,3},

        };

        System.out.println(new MaxProductPath().maxProductPath(grid));

    }
    public int maxProductPath(int[][] grid) {
        return solution1(grid);
    }

    public int solution1(int[][] grid){
        int row=grid.length,column=grid[0].length;
        //0 负数最大 1正数最大
        long[][][] f=new long[row+1][column+1][2];
        for (int i=0;i<=column;i++){
            f[0][i][0]=1;
            f[0][i][1]=-1;
        }
        for (int i=0;i<=row;i++){
            f[i][0][0]=1;
            f[i][0][1]=-1;
        }
        long r=(int)Math.pow(10d,9d)+7;
        for (int i=1;i<=row;i++){
            for (int j=1;j<=column;j++){
                int val=grid[i-1][j-1];
                if (i==1 && j==1){
                    if (val>0){
                        f[i][j][0]=1;
                        f[i][j][1]=val;
                    }else if (val<0){
                        f[i][j][0]=val;
                        f[i][j][1]=-1;
                    }else{
                        f[i][j][0]=f[i][j][1]=0;
                    }
                    continue;
                }
                long up0=f[i-1][j][0],up1=f[i-1][j][1],left0=f[i][j-1][0],left1=f[i][j-1][1];
                f[i][j][0]=1;
                f[i][j][1]=-1;
                if (val>=0){
                    if (up0<=0){
                        f[i][j][0]=up0*val;
                    }
                    if (left0<=0){
                        long x=left0  * val;
                        f[i][j][0]=Math.min(f[i][j][0],x);
                    }
                    if (up1>=0){
                        f[i][j][1]=(val * up1) ;
                    }
                    if (left1>=0){
                        f[i][j][1]=Math.max( f[i][j][1],(val*left1));
                    }
                    if (val==0){
                        f[i][j][0]=Math.min(val,f[i][j][0]);
                        f[i][j][1]=Math.max(val,f[i][j][1]);
                    }
                }else{
                    if (up1>=0){
                        f[i][j][0]=up1 * val;
                    }
                    if (left1>=0){
                        f[i][j][0]=Math.min(f[i][j][0],val*left1);
                    }
                    if (up0<=0){
                        f[i][j][1]=(up0 * val) ;
                    }
                    if (left0<=0){
                        f[i][j][1]=Math.max((left0 * val) ,f[i][j][1]);
                    }
                }
            }
        }
        return (int)(f[row][column][1]==-1 ? -1 : f[row][column][1] % r);

    }


}
