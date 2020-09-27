package com.luff.ltarg.backtracking;

import java.util.*;

/**
 * @author lsq
 * @date 2020/9/18
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 */
public class PermuteUnique {


    public static void main(String[] args) {
        int[] nums=new int[]{1,1,2};
        System.out.println(new PermuteUnique().permuteUnique(nums).size());
        System.out.println(new PermuteUnique().solution2(nums));
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        return solution1(nums);
    }

    public List<List<Integer>> solution1(int[] nums){
        Arrays.sort(nums);
        int len=nums.length;
        Set<Integer> marked=new HashSet<>(len);
        List<List<Integer>> res=new ArrayList<>(len);
        recursive(nums,marked,new ArrayList<>(len),res);
        return res;
    }

    public void recursive(int[] nums,Set<Integer> marked,List<Integer> list,List<List<Integer>> res){
        if (marked.size()==nums.length){
            res.add(new ArrayList<>(list));
            return ;
        }
        int lastChoose=-1;
        for (int i=0;i<nums.length;i++){
            // 当将nums[i]加入到list中，为了保证所得到的排列是不同的，我们必须保证在list的第k位是不同的。
            // 所以只要在此时加入到list的值和上一次加入到list的值是不同的，那么就可以保证最后得到的答案是不同的
            if (!marked.contains(i) && (lastChoose==-1 || nums[i]!=nums[lastChoose])){
                lastChoose=i;
                marked.add(i);
                list.add(nums[i]);
                recursive(nums,marked,list,res);
                list.remove(list.size()-1);
                marked.remove(i);
            }
        }
    }
//===========================================================================
    public List<List<Integer>> solution2(int[] nums){
        Arrays.sort(nums);
        int len=nums.length;
        boolean[] marked=new boolean[len];
        List<List<Integer>> res=new ArrayList<>(len);
        recursive(nums,marked,0,new ArrayList<>(len),res);
        return res;
    }
    public void recursive(int[] nums,boolean[] marked,int n,List<Integer> list,List<List<Integer>> res){
        // 使用数组标记是否visit，而不是使用set
        if (n==nums.length){
            res.add(new ArrayList<>(list));
            return ;
        }
        int lastChoose=-1;
        for (int i=0;i<nums.length;i++){
            if (!marked[i]&& (lastChoose==-1 || nums[i]!=nums[lastChoose])){
                lastChoose=i;
                marked[i]=true;
                list.add(nums[i]);
                recursive(nums,marked,n+1,list,res);
                list.remove(list.size()-1);
                marked[i]=false;
            }
        }
    }
}
