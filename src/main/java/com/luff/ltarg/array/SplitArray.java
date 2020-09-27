package com.luff.ltarg.array;

import java.util.Arrays;

/**
 * @author lsq
 * @date 2020/7/25
 */
public class SplitArray {

    public static void main(String[] args) {
        int[] nums=new int[]{7,2,5,10,8};
        int m=2;
        System.out.println(splitArray(nums,m));
    }

    public static int splitArray(int[] nums, int m) {
        return solution2(nums,m);
    }

    /**
     *
     * 将这个数组分为了m个连续的数组，假设第mi个数组有ai个数据。m0,m1,,,,mm-1，供m个数组分别由数据,a0,a1,,,,am-1
     *
     * @param nums
     * @param m
     * @return
     */
    public static int solution(int[] nums,int m){
        int len=nums.length;
        int[] f=new int[len];
        recursiveSolution(f,nums,0,len-1,m,0);
        return minMax;
    }

    private static int minMax=Integer.MAX_VALUE;
    // 超出时间限制
    public static void recursiveSolution(int[] f,int[] nums,int start,int end,int m,int preMax){
        // 检查start-end的个数是否等于m,如果等于代表，以后的分组都只能分一个

        if (m==1){ //
            int adds=0;
            for (int i=start;i<=end;i++){
                adds+=nums[i];
            }
            adds=Math.max(adds,preMax);
            minMax=Math.min(adds,minMax);
            return;
        }
        if (end-start+1==m){ // 直接比较
            //return Math.max(nums[start],recursiveSolution(nums,start+1,end,m-1));
             recursiveSolution(f,nums,start+1,end,m-1,Math.max(preMax,nums[start]));
             return;
        }
        int max=(end-start+1)-m+1; // 最多个数
        for (int i=1;i<=max;i++){
            int sEnd=start+i;
            int tmpMax=0;
            for (int j=start;j<sEnd;j++){
                tmpMax+=nums[j];
            }
           recursiveSolution(f,nums,start+i,end,m-1,Math.max(preMax,tmpMax));
        }
    }

    /**
     * 可以令 f[i][j] 表示将数组的前 i 个数分割为 j 段所能得到的最大连续子数组和的最小值
     * @param nums
     * @param m
     * @return
     */
    public static int solution2(int[] nums,int m){
        int len=nums.length;
        int[][] f=new int[len+1][m+1];
        for(int i=0;i<=len;i++){
            Arrays.fill(f[i],Integer.MAX_VALUE);
        }
        int[] arr=new int[len+1];
        for (int i=1;i<=len;i++){
            arr[i]=arr[i-1]+nums[i-1];
        }
        f[0][0]=0;
        for (int i=1;i<=len;i++){
            for (int j=1;j<=Math.min(i,m);j++){
                for (int k=0;k<i;k++){
                    f[i][j]=Math.min(f[i][j],Math.max(f[k][j-1],arr[i]-arr[k]));
                }
            }
        }

        return f[len][m];
    }
}
