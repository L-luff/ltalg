package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

/**
 * @Classname SortedArrayToBST
 * @Description
 * @Date 2020/4/21 21:04
 * @Created by li
 */
public class SortedArrayToBST {


    /**
     * 将已经排好序的数据，转化为一颗高度平衡的二叉搜索树
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return toBST(nums,0,nums.length-1);
    }


    public TreeNode toBST(int[] nums,int left,int right){
        if(left>right) return null;
        if(right-left==0) return new TreeNode(nums[left]);
        int mid=left+((right-left)>>1);
        TreeNode cur=new TreeNode(nums[mid]);
        cur.left=toBST(nums,left,mid-1);
        cur.right=toBST(nums,mid+1,right);
        return cur;

    }

}
