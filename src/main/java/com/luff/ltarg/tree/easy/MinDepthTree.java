package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

/**
 * @Classname MinDepthTree
 * @Description
 * @Date 2020/4/22 21:39
 * @Created by li
 */
public class MinDepthTree {


    /**
     * 给定一个二叉树，找出其最小深度。
     *
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        recursiveResolve(root,0);
        return minDepth;
    }


    int minDepth=Integer.MAX_VALUE;
    public void recursiveResolve(TreeNode node,int depth){
        if(node==null) return;
        if(node.left==null && node.right==null){
            minDepth=Math.min(minDepth,depth+1);
            return;
        }
        recursiveResolve(node.left,depth+1);
        recursiveResolve(node.right,depth+1);
    }
}
