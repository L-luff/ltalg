package com.luff.ltarg.tree.medium;

import com.luff.ltarg.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author lsq
 * @date 2020/8/2
 *
 *
 * 114
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 *  
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 */
public class Flatten {

    public void flatten(TreeNode root) {

    }


    public void solution(TreeNode root){

        if (root==null) return ;
        List<TreeNode> nodes=new ArrayList<>();
        preorderTraversal(root,nodes);
        for (int i=1;i<nodes.size();i++){
            TreeNode cur=nodes.get(i);
            TreeNode pre=nodes.get(i-1);
            pre.left=null;
            pre.right=cur;
        }

    }


    public void solution2(TreeNode root){
        if (root==null) return ;
        LinkedList<TreeNode> stack=new LinkedList<>();
        stack.push(root);
        TreeNode prev=null;
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if (prev!=null){
                prev.left=null;
                prev.right=cur;
            }
            TreeNode left=cur.left,right=cur.right;
            if (right!=null){
                stack.push(right);
            }
            if (left!=null){
                stack.push(left);
            }
            prev=cur;
        }
    }

    /**
     * 寻找某节点的前驱节点
     * @param root
     */
    public void solution3(TreeNode root){

        if (root==null) return ;

        TreeNode node=root;
        while (node!=null){
            if (node.left!=null){
                TreeNode left=node.left,pre=node.left;
                while (pre.right!=null){
                    pre=pre.right;
                }
                pre.right=node.right;
                node.left=null;
                node.right=left;
            }
            node=node.right;
        }

    }

    private void preorderTraversal(TreeNode node, List<TreeNode> nodes){
        if (node!=null){
            nodes.add(node);
            preorderTraversal(node.left,nodes);
            preorderTraversal(node.right,nodes);
        }
    }
}
