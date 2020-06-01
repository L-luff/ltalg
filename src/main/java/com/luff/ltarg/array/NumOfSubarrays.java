package com.luff.ltarg.array;

/**
 *
 *
 */
public class NumOfSubarrays {

    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int left=0,right=0,sum=0,res=0;
        while (left+k<=arr.length){
            sum+=arr[right];
            if((right-left+1)==k){
                if((sum / k) >= threshold){
                    res++;
                }
                sum-=arr[left];
                left++;
            }
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(numOfSubarrays(new int[]{4,4,4,4},7,7));
    }

}
