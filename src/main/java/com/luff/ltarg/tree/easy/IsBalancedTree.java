package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

/**
 * @Classname IsBalancedTree
 * @Description
 * @Date 2020/4/22 21:31
 * @Created by li
 */
public class IsBalancedTree {


    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     *
     * 本题中，一棵高度平衡二叉树定义为：
     *
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        recursiveResolve(root);
        return continueResolve;
    }

    boolean continueResolve=true;


    /**
     * 返回值：当前节点+（max(left,right)）=>最长路径
     *
     * 判断当前节点的 左最长路径 和 右最长路径，如果大于1 代表当前树不是高度平衡的，反之当前节点是平衡的。
     *
     * @param node
     * @return
     */
    public int recursiveResolve(TreeNode node){
        if(!continueResolve) return 0;
        if(node==null) return 0;

        int l=recursiveResolve(node.left);
        int r=recursiveResolve(node.right);

        if(Math.abs(l-r)>1){
            continueResolve=false;
            return 0;
        }
        return 1+(Math.max(l,r));
    }
}
