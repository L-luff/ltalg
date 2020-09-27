package com.luff.ltarg.tree.medium;

import com.luff.ltarg.common.TreeNode;

import java.util.*;

/**
 * @author lsq
 * @date 2020/9/14
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 *
 *
 * @see PreorderTraversal
 */
public class InorderTraversal {

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
//        root.left=new TreeNode(3);
//        root.left.left=new TreeNode(1);
        root.right=new TreeNode(2);
        root.right.right=new TreeNode(3);

        System.out.println(new InorderTraversal().inorderTraversal(root));
        System.out.println(new InorderTraversal().iterative2(root));
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root==null) return Collections.emptyList();
        List<Integer> res=new ArrayList<>();
        recursive(root,res);
        return res;
    }

    /**
     * 递归遍历 中序遍历
     * @param node
     * @param res
     */
    public void recursive(TreeNode node,List<Integer> res){
        if (node.left!=null){
            recursive(node.left,res);
        }
        res.add(node.val);
        if (node.right!=null){
            recursive(node.right,res);
        }
    }


    /**
     * 迭代算法
     * @param root
     * @return
     */
    public List<Integer> iterative(TreeNode root){
        List<Integer> res=new ArrayList<>();
        if (root==null){
            return res;
        }
        LinkedList<WrapTreeNode> stack=new LinkedList<>();
        stack.push(new WrapTreeNode(root,true));
        while (!stack.isEmpty()){
            WrapTreeNode wn = stack.peek();
            TreeNode node=wn.node;
            while (node.left!=null && wn.first){
                // 通过模拟线程栈的方法，第一次递归了node的left节点后，将first改为false,
                // 代表该节点的左节点已经递归过了，不需要再次递归左节点，只需要递归右节点即可
                stack.push(new WrapTreeNode(node.left,false));
                node=node.left;
            }
            wn.first=false;
            wn=stack.pop();
            res.add(wn.node.val);
            if (wn.node.right!=null){
                stack.push(new WrapTreeNode(wn.node.right,true));
            }
        }
        return res;
    }
    static class WrapTreeNode{
        TreeNode node;
        boolean first;

        public WrapTreeNode(TreeNode node,boolean first) {
            this.node = node;
            this.first = first;
        }
    }

    /**
     * 迭代方法2
     * @param root
     * @return
     */
    public List<Integer> iterative2(TreeNode root){
        List<Integer> res=new ArrayList<>();
        Deque<TreeNode> stack=new LinkedList<>();
        while (root!=null || !stack.isEmpty()){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            res.add(root.val);
            root=root.right;
        }
        return res;
    }
}
