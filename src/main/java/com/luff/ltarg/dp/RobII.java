package com.luff.ltarg.dp;

//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋
//装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
//
// 示例 1:
//
// 输入: [2,3,2]
//输出: 3
//解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
public class RobII {

    /**
     * 将问题分为两步， i:窃取第一个房屋，那最后一个房屋将不能被访问
     *                ii:不窃取第一个房屋，那最后一个将可以被访问。
     *  将i和ii的最后结果比较最大值即可。
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if(nums==null || nums.length==0)
            return 0;
        if(nums.length==1)
            return nums[0];
        if(nums.length==2)
            return Math.max(nums[0],nums[1]);
        return Math.max(solution(nums,1,nums.length-1),solution(nums,0, nums.length-2));
    }

    public static int solution(int[] nums,int startIndex,int endIndex){
        int first=nums[startIndex],second=Math.max(first,nums[startIndex+1]);
        for (int i=startIndex+2;i<=endIndex;i++){
            int tmp=Math.max(nums[i]+first,second);
            first=second;
            second=tmp;
        }
        return second;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{1,3,1,3,100}));
    }
}
