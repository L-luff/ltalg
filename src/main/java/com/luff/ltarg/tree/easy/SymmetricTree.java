package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Classname SymmetricTree
 * @Description
 * @Date 2020/4/21 19:13
 * @Created by li
 */
public class SymmetricTree {

    /**
     * 判断这棵树是否是对称的。
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        return isSymmetricWithRecursive(root.left,root.right);
    }

    /**
     * 使用递归的方式
     * @param n1
     * @param n2
     * @return
     */
    public boolean isSymmetricWithRecursive(TreeNode n1,TreeNode n2){
        if(n1==null && n2==null) return true;
        if(n1==null || n2==null) return false;
        if(n1.val!=n2.val) return false;
        return isSymmetricWithRecursive(n1.left,n2.right)
                    && isSymmetricWithRecursive(n1.right,n2.left);
    }


    /**
     * 迭代的方式
     * @param node
     * @return
     */
    public boolean isSymmetricWithIterate(TreeNode node){
        Queue<TreeNode> l1=new LinkedList<>();
        Queue<TreeNode> l2=new LinkedList<>();
        l1.offer(node);l2.offer(node);
        while(!l1.isEmpty()&&!l2.isEmpty()){
            TreeNode l1Node = l1.poll();
            TreeNode l2Node = l2.poll();
            if(l1Node==null && l2Node==null) continue;
            if(l1Node==null || l2Node==null) return false;
            if(l1Node.val !=l2Node.val) return false;
            l1.add(l1Node.left);
            l1.add(l1Node.right);
            l2.add(l2Node.right);
            l2.add(l2Node.left);
        }
        if(l1.isEmpty() && l2.isEmpty()) return true;
        return false;
    }

}
