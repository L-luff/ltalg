package com.luff.ltarg.tree.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lsq
 * @date 2020/9/28
 */
public class Connect {
    static class Node{
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        return solution1(root);
    }

    // 使用队列的方式
    public Node solution1(Node root){
        Queue<Node> list=new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()){
            int size=list.size();
            Node pre=null;
            for (int i=0;i<size;i++){
                Node tmp = list.poll();
                tmp.next=pre;
                pre=tmp;
                if (tmp.right!=null){
                    list.add(tmp.right);
                }
                if (tmp.left!=null){
                    list.add(tmp.left);
                }
            }
        }
        return root;
    }

    // 常量级额外空间
    // 假设第i层的所有节点的next节点都已经正常的连接，那么第i+1层的所有节点的next节点可以遍历通过第i层的next节点来完成
    public Node solution2(Node root){
        Node node=root,pre=null;
        while (node!=null) {
            Node tmp=null;
            while (node != null) {
                if (node.left != null && node.right != null) {
                    if (pre!=null)
                        pre.next = node.left;
                    node.left.next = node.right;
                    pre = node.right;
                    tmp=tmp==null?node.left:tmp;
                } else if (node.left != null) {
                    if(pre!=null)
                        pre.next = node.left;
                    pre = node.left;
                    tmp=tmp==null?node.left:tmp;
                } else if (node.right != null) {
                    if (pre!=null)
                        pre.next = node.right;
                    pre = node.right;
                    tmp=tmp==null?node.right:tmp;
                }
                node = node.next;
            }
            node=tmp;
            pre=null;
        }
        return root;
    }
}
