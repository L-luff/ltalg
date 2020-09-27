package com.luff.ltarg.array;

/**
 * @author lsq
 * @date 2020/9/1
 */
public class PredictTheWinner {

    public static void main(String[] args) {
        int[] nums=new int[]{1, 5, 2};
        System.out.println(new PredictTheWinner().PredictTheWinner(nums));
    }

    public boolean PredictTheWinner(int[] nums) {
        return solution1(nums);
    }

    /**
     * 为了判断哪个玩家可以获胜，需要计算一个总分，为先手得分与后手得分之差。当数组中的所有数字都被拿取时，如果总分大于或等于 00，则先手获胜，反之则后手获胜。
     * @param nums
     * @return
     */
    public boolean solution1(int[] nums){
        return recursive(nums,0,nums.length-1,1)>=0;
    }

    /**
     * 可以在递归式记忆化
     * @param nums
     * @param start
     * @param end
     * @param t1
     * @return
     */
    public int recursive(int[] nums,int start,int end,int t1){
        if (start==end) return nums[start] * t1;

        int r1=nums[start] * t1 + recursive(nums,start+1,end,-t1);
        int r2=nums[end] * t1 +recursive(nums,start,end-1,-t1);
        return Math.max(r1 * t1,r2 * t1) * t1;
    }

    /**
     * dp
     * @param nums
     * @return
     */
    public boolean solution2(int[] nums){
        int len=nums.length;
        int f[][]=new int[len][len]; // 索引f[i][j] 最大差值
        for (int i=0;i<len;i++){
            f[i][i]=nums[i];
        }
        for (int i=len-2;i>=0;i--){
            for (int j=i+1;j<len;j++){
                f[i][j]=Math.max(nums[i]-f[i+1][j],nums[j]-f[i][j-1]);
            }
        }
        return f[0][len-1]>=0;
    }
}
