package com.luff.ltarg.backtracking;

import java.util.*;

/**
 * @author lsq
 * @date 2020/9/3
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *  
 *
 * 示例：
 *
 * 输入：4
 * 输出：[
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *  
 *
 * 提示：
 *
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SolveNQueens {

    public static void main(String[] args) {
        int n=9;
        List<List<String>> res1 = new SolveNQueens().solution1(n);
        List<List<String>> res2 = new SolveNQueens().solution2(n);
        System.out.println(res1.size());
        System.out.println(res2.size());
    }

    /**
     * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上
     * 这个斜线代表所有已经选择的表格中，所有的斜线
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        return solution1(n);
    }

    /**
     * @param n
     * @return
     */
    public List<List<String>> solution1(int n){
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<n;i++){
            sb.append(".");
        }
        List<String> solutionList=new ArrayList<>(n);
        List<List<String>> res=new ArrayList<>();
        Map<Integer,Integer> marked=new HashMap<>(); // key : 格子 ，value: 层数 从0计算
       recursive(n,n,sb.toString(),marked,solutionList,res);
        return res;
    }

    public List<List<String>> solution2(int n){
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<n;i++){
            sb.append(".");
        }
        List<String> solutionList=new ArrayList<>(n);
        List<List<String>> res=new ArrayList<>();
        recursive2(n,(1<<n)-1,n,sb.toString(),solutionList,res,0,0,0);
        return res;
    }
    public void recursive(int n,int remain, String replaceStr,
                          Map<Integer,Integer> marked,List<String> solutionList,List<List<String>> res){
        if (remain==0){
            List<String> dst=new ArrayList<>(solutionList.size());
            dst.addAll(solutionList);
            res.add(dst);
            return ;
        }
        Set<Integer> setContain=new HashSet<>(marked.size() * 2);
        int r=n-remain;
        for (Map.Entry<Integer, Integer> entry : marked.entrySet()) {
            setContain.add(entry.getKey());
            setContain.add(entry.getKey()+r-entry.getValue());
            setContain.add(entry.getKey()-(r-entry.getValue()));
        }
        for (int i=0;i<n;i++){
            boolean canAdd = !setContain.contains(i);
            if (canAdd){
                // 计算斜线
//                for (Map.Entry<Integer, Integer> entry : marked.entrySet()) {
//                    int key=entry.getKey(),val=entry.getValue();
//                    canAdd=canAdd && (key+r-val!=i) && (key-(r-val)!=i);
//                    if (!canAdd) break;
//                }

                marked.put(i, n - remain);
                solutionList.add(new StringBuilder(replaceStr).replace(i, i + 1, "Q").toString());
                recursive(n, remain - 1, replaceStr, marked, solutionList, res);
                marked.remove(i);
                solutionList.remove(solutionList.size() - 1);

            }
        }
    }

    /**
     * 基于位运算的回溯
     * column：表示当前列无法放置皇后的值，其中bit中的0代表可以放置皇后，1代表不可以放置皇后
     * diagonal1：计算左下方可以放置皇后的值
     * diagonal2：计算右下方可以放置皇后的值
     *
     * 计算位运算减少了上面使用Map来保存每列保存的皇后的数据
     *
     * @param n
     * @param s
     * @param remain
     * @param replaceStr
     * @param solutionList
     * @param res
     * @param column
     * @param diagonal1
     * @param diagonal2
     */
    // 0:表示可以放置皇后，1：不可以
    public void  recursive2(int n,int s,int remain, String replaceStr,List<String> solutionList,
                            List<List<String>> res,int column,int diagonal1,int diagonal2){
        if (remain==0){
            List<String> dst=new ArrayList<>(solutionList.size());
            dst.addAll(solutionList);
            res.add(dst);
            return ;
        }
        int marked=column | diagonal1 | diagonal2;
        for (int i=0;i<n;i++){
            int pi=1<<(n-i-1);
            if ((pi & marked) == 0){
                solutionList.add(new StringBuilder(replaceStr).replace(i,i+1,"Q").toString());
                recursive2(n,s,remain-1,replaceStr,solutionList,res,column | pi,s & ((diagonal1<<1)|(pi<<1)),s & ((diagonal2>>1)| (pi>>1)));
                solutionList.remove(solutionList.size()-1);
            }
        }

    }
}
