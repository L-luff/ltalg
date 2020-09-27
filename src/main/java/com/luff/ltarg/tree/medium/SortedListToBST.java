package com.luff.ltarg.tree.medium;

import com.luff.ltarg.common.ListNode;
import com.luff.ltarg.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lsq
 * @date 2020/8/18
 */
public class SortedListToBST {

    public static void main(String[] args) {
        int[] val=new int[]{-10,-3,0,5,9};
        ListNode head=new ListNode(-1);
        ListNode tmp=head;
        for (int v:val){
            ListNode node=new ListNode(v);
            tmp.next=node;
            tmp=tmp.next;
        }
        head=head.next;
        TreeNode treeNode = new SortedListToBST().sortedListToBST(head);
        System.out.println(treeNode);
    }

    public TreeNode sortedListToBST(ListNode head) {
        return solution2(head);
    }

    /**
     * 将head链表转为数组，然后递归的构造高度平衡的二叉搜索树
     * @param head
     * @return
     */
    public TreeNode solution(ListNode head){
        List<Integer> nodes=new ArrayList<>();
        ListNode node=head;
        while (node!=null){
            nodes.add(node.val);
            node=node.next;
        }
        return recursive(nodes,0,nodes.size()-1);
    }

    /**
     * 快慢指针
     * @param head
     * @return
     */
    public TreeNode solution2(ListNode head){
        return recursive2(head,null);
    }

    ListNode globalHead;
    /**
     * 中序遍历，直接构造
     * @param head
     * @return
     */
    public TreeNode solution3(ListNode head){
        int len = nodeLen(head);
        globalHead=head;
        TreeNode res = buildTree(0, len - 1);
        return res;
    }

    public TreeNode buildTree(int left,int right){
        if (left>right) return null;
        int mid=left +((right-left)>>1);
        TreeNode root=new TreeNode();
        root.left=buildTree(left,mid-1);
        root.val=globalHead.val;
        globalHead=globalHead.next;
        root.right=buildTree(mid+1,right);
        return root;
    }

    public int nodeLen(ListNode head){
        ListNode tmp=head;
        int len=0;
        while (tmp!=null){
            len++;
            tmp=tmp.next;
        }
        return len;
    }

    public TreeNode recursive2(ListNode left,ListNode right){
        if (left==right) return null;
        ListNode mid=getMid(left,right);
        TreeNode root=new TreeNode(mid.val);
        root.left=recursive2(left,mid);
        root.right=recursive2(mid.next,right);
        return root;
    }

    public ListNode getMid(ListNode left,ListNode right){
        if (left==null) return null;
        ListNode slow=left,fast=left;
        while(fast!=right && fast.next!=right){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    public TreeNode recursive(List<Integer> nodes,int left,int right){
        if (left>right) return null;
        if (left==right) return new TreeNode(nodes.get(left));

        int mid=left+((right-left)>>1);
        TreeNode root=new TreeNode(nodes.get(mid));
        root.left=recursive(nodes,left,mid-1);
        root.right=recursive(nodes,mid+1,right);
        return root;
    }
}
