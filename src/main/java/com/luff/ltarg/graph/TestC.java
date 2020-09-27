package com.luff.ltarg.graph;

import java.util.Arrays;

/**
 * @author lsq
 * @date 2020/7/16
 */
public class TestC {

    public static void main(String[] args) {
      int[] s=new int[]{1,3,5,6};
      int target=0;
        System.out.println(searchInsert(s,target));
    }
    public static int searchInsert(int[] nums, int target) {
        int right=nums.length-1;
        int left=0;
        while(left<=right){
            int mid=left + ((right-left)>>1);
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        return left;
    }

}
