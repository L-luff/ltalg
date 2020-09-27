package com.luff.ltarg.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lsq
 * @date 2020/9/9
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *  
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates=new int[]{2,3,6,7};
        int target=7;
        List<List<Integer>> res = new CombinationSum().solution2(candidates, target);
        System.out.println(res);
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return solution1(candidates,target);
    }

    public List<List<Integer>> solution1(int[] candidates,int target){
        Arrays.sort(candidates);
        List<List<Integer>> res=new ArrayList<>();
        dfs(res,new ArrayList<>(candidates.length),candidates,target,0);
        return res;
    }

    public void dfs(List<List<Integer>> res,List<Integer> list,int[] candidates,int target,int index){
        if (index==candidates.length) return ;
        if (target==0){
            res.add(new ArrayList<>(list));
            return ;
        }
        //组合问题，当我们选择index中的值是，下一次递归依然可以选择。但是如果我们不再选择index,那么下一次递归一定得是index+1
        // 这样保证不会出现重复的答案。


        // 不选择在 index的位置
        dfs(res,list,candidates,target,index+1);

        // 如果target大于此处index位置的值，则选择
        if (target-candidates[index]>=0){
            list.add(candidates[index]);
            dfs(res,list,candidates,target-candidates[index],index);
            list.remove(list.size()-1);
        }
    }


    //==========================================

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
        // 同理，使用begin来防止重复的答案，要么可以一直选择，要么不选择之后，后面的所有递归都不在选择
        for(int i=begin;i<candidates.length;i++){
            if (candidates[i]>target) return ;
            list.add(candidates[i]);
            dfs2(res,list,candidates,target-candidates[i],i);
            list.remove(list.size()-1);
        }
    }
}
