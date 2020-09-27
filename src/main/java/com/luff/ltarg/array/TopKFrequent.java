package com.luff.ltarg.array;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author lsq
 * @date 2020/9/7
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *  
 *
 * 提示：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 */
public class TopKFrequent {

    public static void main(String[] args) {
        int[] nums=new int[]{1};
        int k=1;
        int[] res = new TopKFrequent().topKFrequent(nums, k);
        System.out.println(Arrays.toString(res));
    }
    public int[] topKFrequent(int[] nums, int k) {
        return solution1(nums,k);
    }


    /**
     * 建立最小堆，保证堆的大小始终是k.使用PriorityQueue
     * @param nums
     * @param k
     * @return
     */
    public int[] solution1(int[] nums,int k){
        Map<Integer,Integer> map=new HashMap<>(nums.length);
        for (int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        PriorityQueue<int[]> heap=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num=entry.getKey(),count=entry.getValue();
            if (heap.size()==k){
                int[] peek = heap.peek();
                if (peek[1]<count){
                    heap.poll();
                    heap.add(new int[]{num,count});
                }
            }else{
                heap.add(new int[]{num,count});
            }
        }
        int[] res=new int[k];
        for (int i=0;i<k;i++){
            res[i]=heap.poll()[0];
        }
        return res;
    }
}
