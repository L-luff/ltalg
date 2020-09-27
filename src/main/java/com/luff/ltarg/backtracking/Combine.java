package com.luff.ltarg.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lsq
 * @date 2020/9/8
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 */
public class Combine {

    public static void main(String[] args) {
        int n=4,k=2;
        List<List<Integer>> res = new Combine().combine(n, k);
        System.out.println(res);

    }

    public List<List<Integer>> combine(int n, int k) {
        return solution1(n,k);
    }

    boolean[] marked;
    public List<List<Integer>> solution1(int n,int k){
        marked=new boolean[n];
        List<List<Integer>> res=new ArrayList<>();
        recursive2(res,new ArrayList<>(),1,n,k);
        return res;
    }

    public void recursive(List<List<Integer>> res,List<Integer> list,int start,int n ,int k){

        if (k==0){
//            List<Integer> dst=new ArrayList<>(list.size());
//            dst.addAll(list);
//            res.add(dst);
            res.add(new ArrayList<>(list));
            return ;
        }
        // 只是递归，并没有剪枝，例如当n=7 k=4时，也就是start最大只能是4，因为当start=5时5-7只有3个数，在递归时是没有意义的
        for (int i=start;i<=n;i++){
//            if (!marked[i-1]){
//                marked[i-1]=true;
//                list.add(i);
//                recursive(res,list,i+1,n,k-1);
//                marked[i-1]=false;
//                list.remove(list.size()-1);
//            }
            list.add(i);
            recursive(res,list,i+1,n,k-1);
            list.remove(list.size()-1);
        }
    }

    /**
     * 回溯 剪枝
     * @param res
     * @param list
     * @param start
     * @param n
     * @param k
     */
    public void recursive2(List<List<Integer>> res,List<Integer> list,int start,int n,int k){
        if (k==0){
            res.add(new ArrayList<>(list));
            return ;
        }
        int maxStart=n-k+1;
        for (int i=start;i<=maxStart;i++){
            list.add(i);
            recursive2(res,list,i+1,n,k-1);
            list.remove(list.size()-1);
        }
    }
}
