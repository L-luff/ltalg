package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Classname SumOfLeftLeaves
 * @Description
 * @Date 2020/4/26 19:29
 * @Created by li
 */
public class SumOfLeftLeaves {


    /**
     * 计算给定二叉树的所有左叶子之和
     *
     * 示例：
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        recursive(root,false);
        return sum;
    }

    private int sum=0;

    /**
     * 递归解决
     * @param node
     * @param l
     */
    public void recursive(TreeNode node,boolean l){
        if(node==null) return;
        if(node.left==null && node.right==null ){
            if(l) sum+=node.val;
            return ;
        }
        recursive(node.left,true);
        recursive(node.right,false);
    }


    /**
     * 迭代解决
     * @param node
     * @return
     */
    public int iterator(TreeNode node){
        int sum=0;
        LinkedList<TreeNode> stack=new LinkedList<>();
        LinkedList<Boolean> l=new LinkedList<>();
        stack.push(node);
        l.push(false);
        while(!stack.isEmpty()){
            TreeNode n = stack.pop();
            Boolean ls = l.pop();
            if(n==null) continue;
            if(n.left==null && n.right==null){
                if(ls){
                    sum+=n.val;
                }
            }
            if(n.right!=null){
                stack.push(n.right);
                l.push(false);
            }

            if(n.left!=null){
                stack.push(n.left);
                l.push(true);
            }
        }
        return sum;
    }

}
