package com.luff.ltarg.tree.medium;

import com.luff.ltarg.common.TreeNode;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lsq
 * @date 2020/9/29
 */
public class PostorderTraversal {



    public List<Integer> postorderTraversal(TreeNode root) {
        return solution1(root);
    }

    // 递归解法
    public List<Integer> solution1(TreeNode root){
        List<Integer> res=new ArrayList<>();
        recursive(root,res);
        return res;
    }

    private void recursive(TreeNode node,List<Integer> list){
        if (node==null) return ;
        recursive(node.left,list);
        recursive(node.right,list);
        list.add(node.val);
    }


    static class  Node{
        TreeNode node;
        int access;

        public Node(TreeNode node) {
            this.node = node;
            this.access=1;
        }

        public Node(TreeNode node, int access) {
            this.node = node;
            this.access = access;
        }
    }
    // 迭代
    public List<Integer> solution2(TreeNode root){
        if (root==null) return Collections.emptyList();
        LinkedList<Node> stack=new LinkedList<>();
        List<Integer> res=new ArrayList<>();
        stack.push(new Node(root,0));
        while (!stack.isEmpty()){
            Node tmp=stack.peek();
            TreeNode tmpNode=tmp.node;
            while (tmpNode.left!=null && tmp.access==0){
                stack.push(new Node(tmpNode.left));
                tmpNode=tmpNode.left;
            }
            tmp.access=tmp.access==0?1:tmp.access;
            tmp=stack.peek();
            if (tmp.access==2){
                res.add(tmp.node.val);
                stack.pop();
            }else{
                tmp.access=2;
                if (tmp.node.right!=null) {
                    stack.push(new Node(tmp.node.right,0));
                }else{
                    stack.pop();
                    res.add(tmp.node.val);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.left.left.left=new TreeNode(5);
        List<Integer> res = new PostorderTraversal().solution2(root);
        List<Integer> res2 = new PostorderTraversal().solution1(root);
        System.out.println(res);
        System.out.println(res2);
    }
}
