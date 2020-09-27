package com.luff.ltarg.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author lsq
 * @date 2020/8/15
 *
 * 546
 *
给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。
你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k*k 个积分。
当你将所有盒子都去掉之后，求你能获得的最大积分和。



示例：

输入：boxes = [1,3,2,2,2,3,4,3,1]
输出：23
解释：
[1, 3, 2, 2, 2, 3, 4, 3, 1]
----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
----> [1, 3, 3, 3, 1] (1*1=1 分)
----> [1, 1] (3*3=9 分)
----> [] (2*2=4 分)


提示：

1 <= boxes.length <= 100
1 <= boxes[i] <= 100
 */
public class RemoveBoxes {


    public static void main(String[] args) {
        int[] boxes=new int[]{1,3,2,2,2,3,4,3,1};
        System.out.println(new RemoveBoxes().removeBoxes(boxes));
    }
    public int removeBoxes(int[] boxes) {
        return solution2(boxes);
    }

    /**
     * 暴力算法
     * @param boxes
     * @return
     */
    public int solution(int[] boxes){
       List<Integer> boxesList=new ArrayList<>(boxes.length);
       for (int box:boxes){
           boxesList.add(box);
       }
       return brute(boxesList);
    }

    int[][][] f=new int[100][100][100];

    public int solution2(int[] boxes){
        dp(boxes,0,boxes.length-1,0);
        return f[0][boxes.length-1][0];
    }

    public int dp(int[] boxes,int start,int end,int k){
        if (start>end) return 0;
        if (f[start][end][k]!=0) return f[start][end][k];
        f[start][end][k]=dp(boxes,start,end-1,0)+ (k+1) * (k+1);
        int val=boxes[end];
        for (int i=start;i<end;i++){
            if (boxes[i]==val){
                f[start][end][k]=Math.max(f[start][end][k],dp(boxes,start,i,k+1)+dp(boxes,i+1,end-1,0));
            }
        }
        return f[start][end][k];
    }

    public int brute(List<Integer> boxesList){
        if (boxesList.isEmpty()) return 0;
        int max=0,size=boxesList.size();
        for (int i=0;i<size;i++){
            int tmpMax=0;
            int val=boxesList.get(i);
            int j=i+1;
            while (j<size && boxesList.get(j)==val){j++;}
            int dif=j-i;
            List<Integer> t=new ArrayList<>(size);
            if (i!=0){
                for (int k=0;k<i;k++){
                    t.add(boxesList.get(k));
                }
            }
            if (j<size){
                for (int k=j;k<size;k++){
                    t.add(boxesList.get(k));
                }
            }
            tmpMax=dif * dif + brute(t);
            max=Math.max(tmpMax,max);
        }
        return max;
    }
}
