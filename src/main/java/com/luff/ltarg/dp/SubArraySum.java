package com.luff.ltarg.dp;

/**
 * @Classname SubArraySum
 * @Description
 * @Date 2020/5/15 20:32
 * @Created by li
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 *
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 */
public class SubArraySum {


    /**
     * 给定目标k,找出子序列和=k的个数
     *      s1: 使用前缀和的方法，假设f(i)是指索引nums[0]-nums[i]的和的值，
     *      则f(i)可以表示为 f(i)=f(i-1)+nums[i]. 使用map来存储值f(i)的个数， f(i)-f(t)=k 则代表索引 nums[t+1]-nums[i]的和=k
     *      所以f(t)=f(i)-k  而f(t)可以通过map来索引个数
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> prefixSumMap=new HashMap<>(nums.length);
        prefixSumMap.put(0,1); // 保证当num[i]=k的特殊化
        int prefix=0,res=0;
        for (int i=0;i<nums.length;i++){
            prefix+=nums[i];
            res+=prefixSumMap.getOrDefault(prefix-k,0);
            prefixSumMap.put(prefix,prefixSumMap.getOrDefault(prefix,0)+1);
        }
        return res;
    }
}
