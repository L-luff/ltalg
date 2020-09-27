package com.luff.ltarg.array;

import java.util.*;

/**
 * @author lsq
 * @date 2020/7/13
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *  
 *
 * 说明：
 *
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 *
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 */
public class Intersect {

    public static void main(String[] args) {
        int[] nums1=new int[]{4,9,5};
        int[] nums2=new int[]{9,4,9,8,4};
        int[] res = intersect(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        return solution2(nums1,nums2);
    }

    /**
     * 使用hash计算
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] solution1(int[] nums1,int[] nums2){
        int l1=nums1.length,l2=nums2.length;
        Map<Integer,Integer> map1=new HashMap<>(l1);
        for (int i=0;i<l1;i++){
            map1.put(nums1[i],map1.getOrDefault(nums1[i],0)+1);
        }

        List<Integer> list=new ArrayList<>(Math.min(l1,l2));
        for (int i=0;i<l2;i++){
            Integer val = map1.getOrDefault(nums2[i], 0);
            if (val>0){
                list.add(nums2[i]);
            }
            map1.put(nums2[i],val-1);
        }
        int[] res=new int[list.size()];
        int index=0;
        for (int n:list){
            res[index++]=n;
        }
        return res;
    }

    // 先进行排序，然后使用双指针
    public static int[] solution2(int[] nums1,int[] nums2){
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int len1=nums1.length,len2=nums2.length;
        int[] res=new int[Math.min(len1,len2)];
        int index1=0,index2=0,index=0;
        while (index1<len1 && index2<len2){
            if (nums1[index1]<nums2[index2]){
                index1++;
            }else if (nums1[index1]>nums2[index2]){
                index2++;
            }else{
                int val=nums1[index1];
                index1++;
                index2++;
                res[index++]=val;
            }
        }
        return Arrays.copyOfRange(res,0,index);
    }

}
