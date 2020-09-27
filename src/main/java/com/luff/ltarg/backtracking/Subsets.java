package com.luff.ltarg.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lsq
 * @date 2020/9/20
 */
public class Subsets {

    public static void main(String[] args) {
        int[] nums=new int[]{1,2,3};
//        List<List<Integer>> res = new Subsets().subsets(nums);
        List<List<Integer>> res = new Subsets().solution3(nums);
        System.out.println(res);
    }

    public List<List<Integer>> subsets(int[] nums) {
        return solution1(nums);
    }

    public List<List<Integer>> solution1(int[] nums){
        List<List<Integer>> res=new ArrayList<>();
        if (nums==null || nums.length==0) return res;
        recursive(nums,0,new ArrayList<>(nums.length),res);
        return res;
    }

    public void recursive(int[] nums,int idx,List<Integer> list,List<List<Integer>> res){
        if (idx==nums.length){
            res.add(new ArrayList<>(list));
            return ;
        }
        for (int i=idx;i<nums.length;i++){
            list.add(nums[i]);
            recursive(nums,i+1,list,res);
            list.remove(list.size()-1);
        }
        res.add(new ArrayList<>(list));
    }
    //=================================================

    public void recursive2(int[] nums,int idx,List<Integer> list,List<List<Integer>> res){
        if (idx==nums.length){
            res.add(new ArrayList<>(list));
            return ;
        }
        list.add(nums[idx]);
        recursive2(nums,idx+1,list,res);
        list.remove(list.size()-1);
        recursive2(nums,idx+1,list,res);
    }

    //=============================
    // 使用位运算解法,注意：前提是所有元素都不相同
    //nums序列中的所有元素都有两个状态，要么在子集中，要么不在子集中
    public  List<List<Integer>> solution3(int[] nums){
        List<List<Integer>> res= new ArrayList<>();
        List<Integer> list=new ArrayList<>(nums.length);
        int mask=1<<(nums.length);
        for (int i=0;i<mask;i++){
            list.clear();
            for (int j=0;j<nums.length;j++){
                if ((((1<<j)&i)!=0)) {
                    list.add(nums[j]);
                }
            }
            res.add(new ArrayList<>(list));
        }
        return res;
    }
}
