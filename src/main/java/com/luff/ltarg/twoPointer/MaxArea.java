package com.luff.ltarg.twoPointer;

/**
 * @author: lsq
 */

public class MaxArea {

    /**
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     *
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 返回容器可以储存的最大水量。
     *
     * 说明：你不能倾斜容器。
     */
    public int maxArea(int[] height) {
        int i=0,j=height.length-1;
        int max = 0;
        while(i<j){
            max = height[i] < height[j] ? Math.max(max,(j-i) * height[i++]) : Math.max(max,(j-i) * height[j--]);
        }
        return max;
    }

    public int solution1(int[] height){
        // 超时
        int max = Integer.MIN_VALUE;
        for (int i=0;i<height.length-1;i++){
            for (int j=i+1;j<height.length;j++){
                max = Math.max(max,(j-i) * Math.min(height[i],height[j]));
            }
        }
        return max;
    }
}
