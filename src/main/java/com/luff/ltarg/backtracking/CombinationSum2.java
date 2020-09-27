package com.luff.ltarg.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lsq
 * @date 2020/9/10
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 */
public class CombinationSum2 {


    public static void main(String[] args) {
        int[] candidates=new int[]{2,5,2,1,2};
        int target=5;
        List<List<Integer>> res = new CombinationSum2().solution2(candidates, target);
        System.out.println(res);
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        return solution1(candidates,target);
    }

    public List<List<Integer>> solution1(int[] candidates,int target){
        Arrays.sort(candidates);
        List<List<Integer>> res=new ArrayList<>();
        dfs(res,new ArrayList<>(candidates.length),candidates,target,0);
        return res;
    }

    public void dfs(List<List<Integer>> res,List<Integer> list,int[] candidates,int target,int index){
        if (target==0){
            res.add(new ArrayList<>(list));
            return ;
        }
        if (index>=candidates.length) return;
        // 跳过相等的数据，对于这种组合，并且有相同数据的组合，那么对于相同数据来说，要么一个个的选择，要么不选择，保证选择的相同值的数量是不同的，那么就可以得到不同的组合
        int newIndex=index+1;
        while (newIndex<candidates.length && candidates[newIndex]==candidates[index]){newIndex++;}
        if (newIndex<candidates.length) {
            dfs(res, list, candidates, target, newIndex);
        }
        if (candidates[index]<=target ) {
            list.add(candidates[index]);
            dfs(res, list, candidates, target - candidates[index], index + 1);
            list.remove(list.size() - 1);
        }
    }
    //=====================================


    public List<List<Integer>> solution2(int[] candidates,int target){
        Arrays.sort(candidates);
        List<List<Integer>> res=new ArrayList<>();
        dfs2(res,new ArrayList<>(candidates.length),candidates,target,0);
        return res;
    }

    public void dfs2(List<List<Integer>> res,List<Integer> list,int[] candidates,int target,int begin){
        if (target==0){
            res.add(new ArrayList<>(list));
            return ;
        }

        int last=-1;
        for (int i=begin;i<candidates.length;i++){
            if (candidates[i]>target) return ;
            //过滤，如果之前相同的数据已经回溯过，那么不再继续回溯，否则会出现相同的答案
            if (candidates[i]==last) continue;
            list.add(candidates[i]);
            dfs2(res,list,candidates,target-candidates[i],i+1);
            list.remove(list.size()-1);
            last=candidates[i];
        }
    }
}
