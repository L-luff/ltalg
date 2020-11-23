package com.luff.ltarg.array;

import java.util.*;

/**
 * @author lsq
 * @date 2020/10/5
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 */

public class FourSum {


    public static void main(String[] args) {
        int[] nums=new int[]{1,-1,-1,0};
        System.out.println(new FourSum().threeSum(nums));
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        solution1(nums,0,target,4,new ArrayList<>(4));
        return res;
    }

    List<List<Integer>> res=new ArrayList<>();

    // 回溯
    public void solution1(int[] nums,int idx,int target,int remainCount,List<Integer> list){
        if(idx>=nums.length) return ;
        if(remainCount==2){
            Map<Integer,Integer> map=new HashMap<>(nums.length);
            for(int i=idx;i<nums.length;i++){
                int index=map.getOrDefault(nums[i],-1);
                if(index!=-1){
                    if (index==Integer.MAX_VALUE) continue;
                    res.add(Arrays.asList(list.get(0),list.get(1),nums[index],nums[i]));
                    map.put(nums[i],Integer.MAX_VALUE);
                }else{
                    map.put(target-nums[i],i);
                }
            }
        }else{
            int pre=Integer.MAX_VALUE;
            for(int i=idx;i<nums.length-2;i++){
                if(nums[i]!=pre && (nums[i+1]>=0 && nums[i+1]<=(target-nums[i]))){
                    pre=nums[i];
                    list.add(nums[i]);
                    solution1(nums,i+1,target-nums[i],remainCount-1,list);
                    list.remove(list.size()-1);
                }
            }
        }
    }


    //多重循环
    public List<List<Integer>> solution2(int[] nums,int target){
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;

    }

    // 同四数和同理，三数和
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res=new ArrayList<>();
        for(int i=0;i<nums.length-2;i++){
            if(i>0 && nums[i]==nums[i-1]) continue;
            int val=nums[i];
            if(val+nums[i+1]+nums[i+2]>0){
                break;
            }
            if(val+nums[nums.length-2]+nums[nums.length-1]<0){
                continue;
            }
            int left=i+1,right=nums.length-1;
            while(left<right){
                int tmp=val+nums[left]+nums[right];
                if(tmp==0){
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while(left<right && nums[left]==nums[left+1]){
                        left++;
                    }
                    left++;
                    while(left<right && nums[right]==nums[right-1]){
                        right--;
                    }
                    right--;
                }else if(tmp>0){
                    right--;
                }else{
                    left++;
                }
            }
        }
        return res;

    }

}
