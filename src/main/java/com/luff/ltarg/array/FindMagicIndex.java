package com.luff.ltarg.array;

/**
 * @author lsq
 * @date 2020/7/31
 */
public class FindMagicIndex {


    public int findMagicIndex(int[] nums) {
        int len=nums.length;
        for (int i=0;i<len;i++){
            if (i==nums[i]){
                return i;
            }
        }
        return -1;
    }
}
