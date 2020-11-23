package com.luff.ltarg.array;

import java.util.Arrays;

public class FindMinArrowShots {
    public static void main(String[] args) {
        int[][] nums=new int[][]{
                {9,12},
                {1,10},
                {4,11},
                {8,12},
                {3,9},
                {6,9},
                {6,7},
        };
        System.out.println(new FindMinArrowShots().findMinArrowShots(nums));
    }

    public int findMinArrowShots(int[][] points) {
        if(points.length<2) return points.length;
        Arrays.sort(points, (o1, o2) -> {
            if (o1[0]==o2[0]){
                return Integer.compare(o1[1],o2[1]);
            }
            return Integer.compare(o1[0],o2[0]);
        });
        int ans=1;
        int start=points[0][0],end=points[0][1];
        for(int i=1;i<points.length;i++){
            if(points[i][0]>end){
                ans++;
                start=points[i][0];
                end=points[i][1];
            }else{
                start=Math.max(start,points[i][0]);
                end=Math.min(end,points[i][1]);
            }
        }
        return ans;
    }
}
