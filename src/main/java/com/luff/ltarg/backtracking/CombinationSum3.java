package com.luff.ltarg.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lsq
 * @date 2020/9/11
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-iii
 */
public class CombinationSum3 {

    public static void main(String[] args) {
        int k=3;int n=7;
        List<List<Integer>> res = new CombinationSum3().solution2(k, n);
        System.out.println(res);
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        return solution1(k,n);
    }

    public List<List<Integer>> solution1(int k,int n){
        List<List<Integer>> res=new ArrayList<>();
        dfs(res,new ArrayList<>(k),n,k,1);
        return res;
    }

    public void dfs(List<List<Integer>> res,List<Integer> list,int n,int k,int begin){
        if (n==0){
            if (list.size()==k){
                res.add(new ArrayList<>(list));
            }
            return ;
        }

        for (int i=begin;i<=9;i++){
            if (begin<=n && list.size()<k){
                list.add(i);
                dfs(res,list,n-i,k,i+1);
                list.remove(list.size()-1);
            }
        }
    }

    //=========================

    public List<List<Integer>> solution2(int k ,int n){
        List<List<Integer>> res=new ArrayList<>();
        dfs2(res,new ArrayList<>(k),n,k,1);
        return res;

    }

    public void dfs2(List<List<Integer>> res,List<Integer> list,int n,int k,int begin){
        if (n==0){
            if (list.size()==k){
                res.add(new ArrayList(list));
            }
            return ;
        }
        if (begin>9) return ;

        dfs2(res,list,n,k,begin+1);

        if (begin<=n && list.size()<k){
            list.add(begin);
            dfs2(res,list,n-begin,k,begin+1);
            list.remove(list.size()-1);
        }
    }
}
