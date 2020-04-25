package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Classname InvertTree
 * @Description
 * @Date 2020/4/23 19:14
 * @Created by li
 */
public class InvertTree {


    /**
     * 翻转一颗二叉树
     * 输入：
     *
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 输出：
     *
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        return recursive(root);
    }


    /**
     * 递归结局
     * @param node
     * @return
     */
    public TreeNode recursive(TreeNode node){
        if(node==null) return null;
        TreeNode left=recursive(node.left);
        TreeNode right=recursive(node.right);
        node.left=right;
        node.right=left;
        return node;
    }

    /**
     * 迭代结局
     * @param node
     * @return
     */
    public TreeNode iterator(TreeNode node){
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            TreeNode n = queue.poll();
            if(n==null) continue;
            TreeNode nl=n.left;
            n.left=n.right;
            n.right=nl;
            queue.offer(n.left);
            queue.offer(n.right);
        }
        return node;
    }

}
