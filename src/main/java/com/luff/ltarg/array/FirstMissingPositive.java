package com.luff.ltarg.array;

import java.util.BitSet;

/**
 * @author lsq
 * @date 2020/6/27
 *
 * 要求： 时间复杂度 O(n)  空间复杂度 O(1)
 */
public class FirstMissingPositive {


    public  static int firstMissingPositive(int[] nums) {
        for (int i=0;i<nums.length;i++){
            if(nums[i]<=0){
                nums[i]=nums.length+1;
            }
        }

        for (int i=0;i<nums.length;i++){
            int val=Math.abs(nums[i]);
            if(val<=nums.length){
                nums[val-1]=-Math.abs(nums[val-1]);
            }
        }

        for (int i=0;i<nums.length;i++){
            if(nums[i]>0){
                return i+1;
            }
        }


        return nums.length+1;
    }

    /**
     *  memory error
     * @param nums
     * @return
     */
    public static int bitSetSolution(int[] nums){
        BitSet bitSet=new BitSet();
        for (int v:nums){
            if(v>0)
                bitSet.set(v);
        }

        int size = bitSet.size();
        for (int i=1;i<size;i++){
            if(!bitSet.get(i)){
                return i;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{7};
        System.out.println(firstMissingPositive(nums));
    }
}
