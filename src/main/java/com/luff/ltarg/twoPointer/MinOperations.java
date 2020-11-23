package com.luff.ltarg.twoPointer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MinOperations {

    public static void main(String[] args) {
        int[] nums=new int[]{8828,9581,49,9818,9974,9869,9991,10000,10000,10000,9999,9993,9904,8819,1231,6309};
        System.out.println(new MinOperations().minOperations(nums,134365));
    }
    public int minOperations(int[] nums, int x) {
        return solution1(nums,x);
    }

    /**
     * 1. 利用前缀和，后缀和和twosum的思想
     *    定义前缀数组pre[i] 表示前i+1个数据之和
     *    定义后缀数组suf[i] 表示后面i+1个数据之后
     *    定义map,将suf后缀和的值和index分别做为key,value存储到map中
     *    循环pre,那么就知道了前缀后的值，根据题意，只要找到x-pre[i]的值就可，
     *  而这个值直接查看是否在map即可。
     **/
    public int solution1(int[] nums,int x){
        int[][] arr=new int[3][2];
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0]!=o2[0]){
                return Integer.compare(o1[1],o2[1]);
            }
            return Integer.compare(o1[0],o1[1]);
        });
        int[] pre=new int[nums.length+1];
        for(int i=1;i<pre.length;i++){
            pre[i]= nums[i-1]+pre[i-1];
        }
        int[] suf=new int[nums.length+1];
        int idx=1;
        Map<Integer,Integer> map=new HashMap<>(nums.length);
        for(int i=nums.length-1;i>=0;i--){
            suf[idx]=suf[idx-1]+nums[i];
            map.put(suf[idx],idx);
            idx++;
        }
        if(pre[nums.length] < x ) return -1;
        int min=Integer.MAX_VALUE;
        // 前缀和+后缀和=x ,就有可能只有前缀和=x或者后缀和=x
        //所有pre.length=nums.length+1,留下第一个元素pre[0]=0,获取是否有后缀和=x
        // 前缀和直接判断即可
        for(int i=0;i<pre.length;i++){
            if(pre[i]==x){
                min=Math.min(min,i);
            }else {
                idx = map.getOrDefault(x - pre[i], -1);
                if (idx != -1 && (i+idx)<nums.length) {
                    min = Math.min(min, idx + i);
                }
            }
        }
        return min==Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * 双指针
     * @param nums
     * @param x
     * @return
     */
    public int solution2(int[] nums,int x){
        return -1;
    }
}
