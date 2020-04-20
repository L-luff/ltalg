package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Classname IsSameTree
 * @Description
 * @Date 2020/4/19 13:10
 * @Created by li
 */
public class IsSameTree {

    /**
     * 给定两个二叉树，判断他们是否相等，如果两棵树在结构上和节点值都是相同的，那么认为他们时相等的。
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null)
            return true;
        if(p==null || q==null)
            return false;
        if(p.val!=q.val)
            return false;
        return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }

    public boolean isSameTree2(TreeNode p,TreeNode q){
        if(!(p!=null &&q!=null))
            return false;
        Queue<TreeNode> pQueue=new LinkedList<>();
        Queue<TreeNode> qQueue=new LinkedList<>();
        pQueue.offer(p);
        qQueue.offer(q);
        while(!pQueue.isEmpty() && !pQueue.isEmpty()){
            TreeNode pTemp = pQueue.poll();
            TreeNode qTemp = qQueue.poll();
            if(pTemp.val!=qTemp.val)
                return false;
            if(pTemp.left!=null && qTemp.left!=null){
                pQueue.offer(pTemp.left);
                qQueue.offer(qTemp.left);
            }else if(!(pTemp.left==null && qTemp.left==null))
                return false;

            if(pTemp.right!=null && qTemp.right!=null){
                pQueue.offer(pTemp.right);
                qQueue.offer(qTemp.right);
            }else if(!(pTemp.right==null && qTemp.right==null))
                return false;
        }
        if(qQueue.isEmpty() && pQueue.isEmpty())
            return true;
        return false;
    }

    public static void main(String[] args) {
        String s=new String("abcdefghi");
        String intern = s.intern();
        String s2="abcdefghi";
        System.out.println(intern==s2);
    }
}


