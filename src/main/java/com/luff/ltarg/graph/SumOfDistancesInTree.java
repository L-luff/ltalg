package com.luff.ltarg.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author lsq
 * @date 2020/10/6
 *给定一个无向、连通的树。树中有 N 个标记为 0...N-1 的节点以及 N-1 条边 。
 *
 * 第 i 条边连接节点 edges[i][0] 和 edges[i][1] 。
 *
 * 返回一个表示节点 i 与其他所有节点距离之和的列表 ans。
 *
 * 示例 1:
 *
 * 输入: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * 输出: [8,12,6,10,10,10]
 * 解释:
 * 如下为给定的树的示意图：
 *   0
 *  / \
 * 1   2
 *    /|\
 *   3 4 5
 *
 * 我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * 也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
 * 说明: 1 <= N <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-distances-in-tree
 */
public class SumOfDistancesInTree {


    public static void main(String[] args) {
        int N=5;
        int[][] edges=new int[][]{
                {2,0},
                {4,2},
                {3,1},
                {1,0},
        };
        int[] res = new SumOfDistancesInTree().sumOfDistancesInTree(N, edges);
        Arrays.stream(res).forEach(value -> System.out.print(value+"    "));
        System.out.println();
    }

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        return solution1(N, edges);
    }


    // 超出内存限制
    public int[] solution1(int N,int[][] edges){
        String s="";
        List<Integer> list=new ArrayList<>(100);
        List<List<Integer>> graph=new ArrayList<>(N);
        int[][] f=new int[N][N]; //节点i-j的距离
        int[] ans=new int[N];
        for (int i=0;i<N;i++){
            graph.add(new ArrayList<>(N));
        }
        for (int [] edge:edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            f[edge[0]][edge[1]]=1;
            f[edge[1]][edge[0]]=1;
        }
        boolean[] accessor=new boolean[N];
        for (int i=0;i<N;i++){
            int res=0;
            for (int j=0;j<N;j++){
                if (i==j) continue;
                find=false;
                res+=distance(i,j,graph,f,accessor);
            }
            ans[i]=res;
        }
        return ans;
    }

    boolean find=false;
    private int distance(int i,int j,List<List<Integer>> graph,int[][] f,boolean[] accessor){
        if (f[i][j]!=0) {
            find=true;
            return f[i][j];
        }
        if (f[j][i]!=0){
            find=true;
            return f[j][i];
        }
        accessor[i]=true;
        int res=1;
        for (int v:graph.get(i)){
            if (accessor[v]) continue;
            res=1;
            if (v==j){
                find=true;
                return 1;
            }else{
                res+=distance(v,j,graph,f,accessor);
                if(find) {
                    f[i][j] = res;
                    f[j][i] = res;
                    break;
                }
            }
        }
        accessor[i]=false;
        return res;
    }

}
