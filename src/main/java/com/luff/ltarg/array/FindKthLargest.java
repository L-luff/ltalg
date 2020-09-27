package com.luff.ltarg.array;

import java.util.Arrays;

/**
 * @author lsq
 * @date 2020/6/29
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4

 */
public class FindKthLargest {

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3,2,3,1,2,4,5,5,6},4));
    }

    public static int findKthLargest(int[] nums, int k) {
        return solution1(nums,k);
    }

    public static int solution1(int[] nums,int k){
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
}
