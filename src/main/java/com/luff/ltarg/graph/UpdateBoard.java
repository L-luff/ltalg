package com.luff.ltarg.graph;

/**
 * @author lsq
 * @date 2020/8/20
 * 让我们一起来玩扫雷游戏！
 *
 * 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
 *
 * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
 *
 * 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
 * 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
 * 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
 * 如果在此次点击中，若无更多方块可被揭露，则返回面板。
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minesweeper
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UpdateBoard {

    public static void main(String[] args) {
        char[][] board=new char[][]{
                {'B', '1', 'E', '1', 'B'},
                {'B', '1', 'M', '1', 'B'},
                {'B', '1', '1', '1', 'B'},
                {'B', 'B', 'B', 'B', 'B'}
        };
        char[][] res=new UpdateBoard().updateBoard(board,new int[]{1,2});
        System.out.println(res);
    }

    public char[][] updateBoard(char[][] board, int[] click) {
        return solution1(board,click);
    }

    int[] px=new int[]{-1,-1,0,1,1,1,0,-1};
    int[] py=new int[]{0,-1,-1,-1,0,1,1,1};
    boolean[][] marked;
    boolean end= false;
    public char[][] solution1(char[][] board,int[] click){
        int row=board.length-1,col=board[0].length-1;
        marked=new boolean[row+1][col+1];
        int x=click[0],y=click[1];
        dfs(board,row,col,x,y);
        return board;
    }

    public void dfs(char[][] board,int row,int col,int x,int y){
        char clicks=board[x][y];
        if (clicks=='M'){
            board[x][y]='X';
            end=true;
            return ;
        }
        marked[x][y]=true;
        if (clicks=='E') {
            int count = countAdjacentMines(board, row, col, x, y);
            if (count > 0) {
                board[x][y] = (char) (count + '0');
                return;
            }
            for (int i = 0; i < px.length; i++) {
                int nx=x+px[i],ny=y+py[i];
                if (nx<0 || nx>row || ny<0 || ny>col) continue ;
                if(marked[nx][ny]) continue;
                dfs(board, row, col, nx, ny);
                if (end) break ;
            }
            board[x][y]='B';
        }
        return ;

    }

    private int countAdjacentMines(char[][] board,int row,int col,int x,int y){
        int res=0;
        for (int i=0;i<px.length;i++){
            int nx=x+px[i],ny=y+py[i];
            if (nx>=0 && nx<=row && ny>=0 && ny<=col && board[nx][ny]=='M'){
                res++;
            }
        }
        return res;
    }
}
