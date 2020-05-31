package com.luff.ltarg.dp;

import java.util.LinkedList;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class LargestRectangleArea {

    public static int largestRectangleArea(int[] heights) {
        return 0;
    }

    /**
     * 暴力算法解决 O(n^2)时间复杂度，O（1)空间复杂度
     * @param heights
     * @return
     */
    public static int bruteForce(int[] heights){
        int res=0;
        for (int i=0;i<heights.length;i++){
            int min=heights[i],width=1;
            for (int j=i+1;j<heights.length;j++){
                width++;
                if(heights[j]<min){
                    min=heights[j];
                }
                res=Math.max(res,min * width);
            }
        }
        return res;
    }

    /**
     *
     * 进栈元素：无法确定该元素进行扩散的最大面积
     * 出栈元素：能够确定该元素扩散的最大面积
     *
     * 出栈条件：A元素小于栈顶元素，使得栈顶元素无法扩散，达到最大面积
     *
     * @param heights
     * @return
     */
    public static int stackSolution(int[] heights){
        LinkedList<Integer> stack=new LinkedList<>();
        int[] left=new int[heights.length];
        int[] right=new int[heights.length];
        for (int i=0;i<heights.length;i++){

            while(!stack.isEmpty() && heights[stack.peek()]>=heights[i]){
                stack.pop();
            }
            left[i]=stack.isEmpty()?-1:stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i=heights.length-1;i>=0;i--){

            while(!stack.isEmpty() && heights[stack.peek()]>=heights[i]){
                stack.pop();
            }
            right[i]=stack.isEmpty()?heights.length:stack.peek();
            stack.push(i);
        }

        int res=0;
        for (int i=0;i<heights.length;i++){
            res=Math.max(res,(right[i]-left[i]-1) * heights[i]);
        }


        return res;
    }

    /**
     *
     * @param heights
     * @return
     */
    public static int dpSolution(int[] heights){
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(bruteForce(new int[]{2,1,5,6,2,3}));
        System.out.println(stackSolution(new int[]{2,1,5,6,2,3}));
    }
}
