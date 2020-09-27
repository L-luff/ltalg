package com.luff.ltarg.recusion;

import java.util.*;

/**
 * @author lsq
 * @date 2020/8/25
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 *
 * 示例:
 *
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * 说明:
 *
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-subsequences
 */
public class FindSubsequences {

    public static void main(String[] args) {
        int[] nums=new int[]{100,90,80,70,60,50,60,70,80,90,100};
        List<List<Integer>> res = new FindSubsequences().findSubsequences(nums);

        System.out.println(res);
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        return solution1(nums);
    }

    Map<Integer,List<List<Integer>>> map=new HashMap<>(256);

    /**
     * 采用递归的形式，定义Map<Integer,List<List<Integer>> 其中map中的key代表数组的索引，将递归产生的数据存储起来
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> solution1(int[] nums){
        int len=nums.length;

        Set<Integer> set=new HashSet<>();
        List<List<Integer>> res=new ArrayList<>();
        for (int i=0;i<len-1;i++){
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
                List<List<Integer>> tmp = map.getOrDefault(i,recursive(i,nums));
                if (tmp!=null){
                    res.addAll(tmp);
                }
            }
        }
        return res;
    }

    public List<List<Integer>> recursive(int preIndex,int[] nums){
        if (preIndex+1==nums.length) return null;
        int val=nums[preIndex];
        if (map.containsKey(preIndex)) return map.get(preIndex);
        List<List<Integer>> res=new ArrayList<>();
        // 保证迭代时的时候，如果迭代时索引i的值和前面已经迭代的值相同，那么索引i忽略，因为所有的数据都通过之前的一次迭代获取了，不再重复

        Set<Integer> set=new HashSet<>();
        for (int i=preIndex+1;i<nums.length;i++){
            int v=nums[i];
            if (v>=val && !set.contains(v)){
                set.add(v);
                res.add(Arrays.asList(v));
                List<List<Integer>> recursive = recursive(i, nums);
                if (recursive != null) {
                    res.addAll(recursive);
                }

            }
        }
        List<List<Integer>> t=new ArrayList<>(res.size());
        for (int i=0;i<res.size();i++){
            List<Integer> tmp=new ArrayList<>(res.get(i).size()+1);
            tmp.add(val);
            tmp.addAll(res.get(i));
            t.add(tmp);
        }
        map.put(preIndex,t);
        return t;
    }


}
