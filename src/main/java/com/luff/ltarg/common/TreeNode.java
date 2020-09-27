package com.luff.ltarg.common;

/**
 * @Classname TreeNode
 * @Description
 * @Date 2020/4/19 13:10
 * @Created by li
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(){}
    public TreeNode(int x){
        val=x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
