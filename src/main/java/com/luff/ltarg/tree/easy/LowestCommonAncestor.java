package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

/**
 * @Classname LowestCommonAncestor
 * @Description
 * @Date 2020/4/24 19:35
 * @Created by li
 */
public class LowestCommonAncestor {


    /**
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

     百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

     说明:
     所有节点的值都是唯一的。
     p、q 为不同节点且均存在于给定的二叉搜索树中。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        recursive(root,p,q);
        return find;
    }

    private TreeNode find;
    public boolean recursive(TreeNode node,TreeNode p,TreeNode q){
        if(node==null) return false;
        if(find!=null) return true;

        boolean left = recursive(node.left, p, q);//左子树可能已经找到了其中一个节点
        if(node.val==p.val || node.val==q.val){ //判断 当前节点是否和两个节点之一相等
            if(left){ // 如果当前节点和另外两个节点之一相等，并且，左子节点也找到了其中一个相等值，那么当前节点就是最近的父节点
                find=node;
                return true;
            }
            left=true;

        }
        boolean right = recursive(node.right, p, q);
        if(left && right && find==null){
            find=node;
        }
        return left|right;
    }


    /**
     * 解法2： 利用二叉搜索树的性值。node节点永远比左子节点大，小于右子节点
     *
     *   假设有节点n 并且节点n在p q之上。  有以下3中可能
     *          1：p q是节点n的左边。那么 n的值将大于p q
     *          2:p q 是节点n的右边 n的值将小于 p q
     *          3: 节点n是p q的父节点。即最近祖先
     *
     * @param node
     * @param p
     * @param q
     * @return
     */
    public void resolve2(TreeNode node,TreeNode p,TreeNode q){
        if((node.val-p.val) * (node.val-q.val)<=0) find=node; //在p q 之上且中间
        else if(node.val<p.val && node.val<q.val) resolve2(node.right,p,q);//节点n最小，所以p q在节点n右下方
        else resolve2(node.left,p,q);
    }

    //使用迭代的方式 同resolve2思想一致。判断节点值即可
    public TreeNode resolve3(TreeNode node,TreeNode p,TreeNode q){
        int pVal=p.val,qVal=q.val;

        while(node!=null){
            int tmpVal=node.val;
            if(tmpVal>pVal && tmpVal>qVal){
                node=node.left;
            }else if(tmpVal<pVal && tmpVal<qVal){
                node=node.right;
            }else{
                return node;
            }
        }
        return null;
    }
}
