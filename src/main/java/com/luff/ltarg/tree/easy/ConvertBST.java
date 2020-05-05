package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;

/**
 * @Classname ConvertBST
 * @Description
 * @Date 2020/5/4 11:41
 * @Created by li
 */

/**
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 *  
 *
 * 例如：
 *
 * 输入: 原始二叉搜索树:
 *               5
 *             /   \
 *            2     13
 *
 * 输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13

 */
public class ConvertBST {

    /**
     * 将一颗二叉搜索树转为一颗累加树。使得每个节点值=原来的节点值+所有大于原来节点值的值
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        iterator(root);
        return root;
    }

    int prefixSum=0;

    /**
     * 遍历右-中-左即可。 将其想象称一个有序的数组。原题意即可变为：在一个有序数组上，将任意索引i处，使得arr[i]=arr[i]+arr[n] 其中n>i && n<arr.len
     * @param node
     */
    public void recursive(TreeNode node){
        if(node==null) return ;

        recursive(node.right);
        int curval=node.val;
        node.val+=prefixSum;
        prefixSum+=curval;
        recursive(node.left);
    }

    //迭代算法：
    public void iterator(TreeNode node){
        LinkedList<TreeNode> stack=new LinkedList<>();
        TreeNode tmp=node;
        while(!stack.isEmpty()||tmp!=null){
            while(tmp!=null){
                stack.push(tmp);
                tmp=tmp.right;
            }
            tmp=stack.pop();
            int curVal=tmp.val;
            tmp.val+=prefixSum;
            prefixSum+=curVal;
            tmp=tmp.left;
        }
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(5);
        root.left=new TreeNode(2);
        root.right=new TreeNode(13);
        TreeNode treeNode = new ConvertBST().convertBST(root);
        System.out.println(treeNode);
    }

    // mirror算法


}
