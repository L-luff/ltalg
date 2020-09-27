package com.luff.ltarg.tree.easy;

import com.luff.ltarg.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Classname BinaryTreePaths
 * @Description
 * @Date 2020/4/25 13:54
 * @Created by li
 */
public class BinaryTreePaths {


    /**给定一个二叉树，返回所有从根节点到叶子节点的路径。

     说明: 叶子节点是指没有子节点的节点。

     示例:

     输入:

     1
     /   \
     2     3
     \
     5

     输出: ["1->2->5", "1->3"]
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res=new ArrayList<>();
        recursive(res,root,new StringBuilder());
        return res;
    }

    // 修改为stringbuilder
//    public void recursive(List<String> res,TreeNode node,String prefix){
//        if(node==null) return;
//        if(node.left==null && node.right==null){
//            res.add(prefix+node.val);
//            return;
//        }
//        String next=new StringBuilder(prefix).append(node.val).append("->").toString();
//        recursive(res,node.left,next);
//        recursive(res,node.right,next);
//    }
    public void recursive(List<String> res,TreeNode node,StringBuilder prefix){
        if(node==null) return;
        if(node.left==null && node.right==null){
            res.add(prefix.append(node.val).toString());
            return;
        }
        prefix.append(node.val).append("->");
        recursive(res,node.left,new StringBuilder(prefix));
        recursive(res,node.right,new StringBuilder(prefix));
    }


    public void iterator(List<String> res,TreeNode node){
        if (node==null) return ;
        Queue<Node> nodes=new LinkedList<>();
        nodes.offer(new Node("",node));
        while (!nodes.isEmpty()){
            int size=nodes.size();
            for (int i=0;i<size;i++){
                Node pollNode = nodes.poll();
                String newPrefix=new StringBuilder().append(pollNode.prefix).append(pollNode.treeNode.val).append("->").toString();
                if (pollNode.treeNode.left==null && pollNode.treeNode.right==null){
                    res.add(pollNode.prefix+pollNode.treeNode.val);
                    continue;
                }
                if (pollNode.treeNode.left!=null){
                    Node newNode=new Node(newPrefix,pollNode.treeNode.left);
                    nodes.offer(newNode);
                }
                if (pollNode.treeNode.right!=null){
                    Node newNode=new Node(newPrefix,pollNode.treeNode.right);
                    nodes.offer(newNode);
                }
            }
        }
    }
    static class Node{
        String prefix;
        TreeNode treeNode;
        public Node(){}
        public Node(String prefix, TreeNode treeNode) {
            this.prefix = prefix;
            this.treeNode = treeNode;
        }
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.right=new TreeNode(5);
        List<String> res=new ArrayList<>();
        new BinaryTreePaths().iterator(res,root);
        System.out.println(res);
    }
}
