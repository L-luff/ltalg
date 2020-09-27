package com.luff.ltarg.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author lsq
 * @date 2020/7/14
 */
public class MinimumTotal {


    public static void main(String[] args) {

    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        return solution(triangle);
    }

    public static int solution(List<List<Integer>> triangle){
        if (triangle.size()==0) return 0;

        //对于索引i的最小路径和
        int[] f=new int[triangle.get(triangle.size()-1).size()];
        Arrays.fill(f,Integer.MAX_VALUE);
        int row=triangle.size();
        for (int i=0;i<row;i++){
            List<Integer> list=triangle.get(i);
            for (int j=list.size()-1;j>=0;j--){
                if (i==0){
                    f[j]=list.get(j);
                    continue;
                }
                // 同一位置或者上一个位置
                if (j==list.size()-1){
                    f[j]=list.get(j)+f[j-1];
                    continue;
                }
                if (j==0){
                    f[j]=list.get(0)+f[0];
                    continue;
                }

                f[j]=Math.min(f[j-1],f[j])+list.get(j);

            }
        }
        int min=Integer.MAX_VALUE;
        for (int i=0;i<f.length;i++){
            min=Math.min(min,f[i]);
        }
        return min;
    }


}
