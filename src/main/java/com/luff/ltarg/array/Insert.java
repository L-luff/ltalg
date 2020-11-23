package com.luff.ltarg.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 给出一个无重叠的 ，按照区间起始端点排序的区间列表。

 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。



 示例 1：

 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 输出：[[1,5],[6,9]]
 示例 2：

 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 输出：[[1,2],[3,10],[12,16]]
 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。


 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。
 */
public class Insert {

    public static void main(String[] args) {
        int[][] intervals=new int[][]{
                {1,2},
                {3,5},
                {6,7},
                {8,10},
                {12,16},
        };
        int[] newInterval=new int[]{-1,-2};
        int[][] res = new Insert().insert(intervals, newInterval);
        for (int i=0;i<res.length;i++){
            System.out.println(Arrays.toString(res[i]));
        }
    }

    // 暴力解法。
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals==null || intervals.length==0){
            return new int[][]{newInterval};
        }
        if (newInterval==null || newInterval.length==0){
            return intervals;
        }
        int leftIndex=idx(intervals,newInterval[0]);
        int rightIndex=idx(intervals,newInterval[1]);
        List<int[]> ans=new ArrayList<>(intervals.length);
        if (leftIndex<intervals.length && intervals[leftIndex][0]<=newInterval[0]
                && intervals[leftIndex][1]>=newInterval[0]){
            //在区间内
            for(int i=0;i<leftIndex;i++){
                ans.add(intervals[i]);
            }
            if(rightIndex<intervals.length && intervals[rightIndex][0]<=newInterval[1]
                && intervals[rightIndex][1]>=newInterval[1]){
                // 右边也在区间内
                ans.add(new int[]{intervals[leftIndex][0],intervals[rightIndex][1]});
                for (int i=rightIndex+1;i<intervals.length;i++){
                    ans.add(intervals[i]);
                }
            }else{
                //右边不在区间内，可以是最后一个，也可能是其中某个索引
                ans.add(new int[]{intervals[leftIndex][0],newInterval[1]});
                for (int i=rightIndex;i<intervals.length;i++){
                    ans.add(intervals[i]);
                }
            }
        }else{// 不在区间内，有可能在某个数组之间，或者最小

           if (rightIndex<intervals.length && intervals[rightIndex][0]<=newInterval[1]
                && intervals[rightIndex][1]>=newInterval[1]){ // 右边值在某个区间范围中
                for (int i=0;i<leftIndex;i++){
                    ans.add(intervals[i]);
                }
                ans.add(new int[]{newInterval[0],intervals[rightIndex][1]});
                for (int i=rightIndex+1;i<intervals.length;i++){
                    ans.add(intervals[i]);
                }
           }else{ // 右边也不再某个区间中
               for (int i=0;i<leftIndex;i++){
                   ans.add(intervals[i]);
               }
               ans.add(new int[]{newInterval[0],newInterval[1]});
               for (int i=rightIndex;i<intervals.length;i++){
                   ans.add(intervals[i]);
               }
           }

        }
        int[][] res=new int[ans.size()][2];
        for (int i=0;i<ans.size();i++){
            res[i]=ans.get(i);
        }
        return res;
    }

    public int idx(int[][] intervals,int val){
        int left=0,right=intervals.length;
        while(left<right){
            int mid=left + ((right-left)>>1);
            if(intervals[mid][0]<=val && intervals[mid][1]>=val){
                return mid;
            }else if(intervals[mid][0]>val){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;
    }
}
