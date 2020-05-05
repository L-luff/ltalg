package com.luff.ltarg.tree.medium;

/**
 * @Classname IsValidBST
 * @Description
 * @Date 2020/5/5 12:25
 * @Created by li
 */

import com.luff.ltarg.common.TreeNode;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。

 */
public class IsValidBST {

    /**
     * 验证是否是一颗有效的二叉搜索树
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        recursive(root);
        return isValid;
    }

    TreeNode prefix=null;
    boolean isValid=true;

    /**
     * 因为时一颗二叉搜索树，所以同理可以将这棵树想象成一个有序数组。即，如果要验证是否有效的。则只要证明这个数组有有序的即可。
     *
     * 保证任意数组索引a,b 当a<b 时 arr[a]<arr[b] 则这个数组就是有序的
     *
     * @param node
     */
    public void  recursive(TreeNode node){
        if(node==null) return ;

        recursive(node.left);
        if(prefix!=null){
            if(!(node.val>prefix.val)){
                isValid=false;
            }
        }
        prefix=node;

        recursive(node.right);
    }
}
