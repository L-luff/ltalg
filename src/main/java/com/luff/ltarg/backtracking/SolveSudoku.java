package com.luff.ltarg.backtracking;

import java.util.*;

/**
 * @author lsq
 * @date 2020/9/15
 */
public class SolveSudoku {

    public void solveSudoku(char[][] board) {

    }

    /**
     * 使用递归，每次都递归字符为'.'的位置，如果改位置数据有冲突，则进行回溯
     * @param board
     */
    public void solution1(char[][] board){
        int row=board.length,column=board[0].length;
        List<int[]> list=new ArrayList<>(); // 记录所有字符为 ’.‘的位置，然后根据这些位置依次递归
        // 以下3个数据结构都可以通过使用位运算来将二维数组改为一维数组
        boolean[][] rows=new boolean[9][9]; // 记录某行是否有1-9中的某个数据 例如： rows[2][5]=true: 第3行已经有数字5了，下面的数据结构类似
        boolean[][] cols=new boolean[9][9]; // 记录某列是否有1-9中的某个数据
        boolean[][] blocks=new boolean[9][9];// 记录这9个方块中某个方块是否有1-9中某个数据
        for (int i=0;i<row;i++){
            for (int j=0;j<column;j++){
                if (board[i][j]=='.'){
                    list.add(new int[]{i,j});
                }else{
                    int p=(i/3)*3+(j/3);
                    int num=board[i][j]-'0'-1;
                    blocks[p][num]=true;
                    rows[i][num]=true;
                    cols[j][num]=true;
                }
            }
        }
        dfs(board,0,list,rows,cols,blocks);
    }
    boolean valid=false;
    private void dfs(char[][] board,int p,List<int[]> list,
                     boolean[][] rows,boolean[][] columns,boolean[][] blocks){

        if (p==list.size()){
            valid=true;
            return ;
        }
        int[] ps = list.get(p);
        int x=ps[0],y=ps[1];
        for (int i=0;i<9 && !valid;i++){
            if (!rows[x][i] && !columns[y][i] &&  !blocks[((x/3)*3+(y/3))][i]){
                rows[x][i]=columns[y][i]=blocks[((x/3)*3+(y/3))][i]=true;
                board[x][y]=(char)(i+'0'+1);
                dfs(board,p+1,list,rows,columns,blocks);
                rows[x][i]=columns[y][i]=blocks[((x/3)*3+(y/3))][i]=false;
            }
        }
    }

}
