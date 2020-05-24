package com.luff.ltarg.list;

import com.luff.ltarg.common.ListNode;

/**
 * @Classname ReverseKGroup
 * @Description
 * @Date 2020/5/16 12:45
 * @Created by li
 */

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 示例：
 *
 * 给你这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 */
public class ReverseKGroup {

    /**
     * 解法1
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tmpHead=head,tmpLast=head;
        NodePair pair=null;
        for (int i=0;i<k;){
            if(tmpLast==null) break;
            if(i+1==k){
                ListNode nextHead=tmpLast.next;
                tmpLast.next=null;
                NodePair nodePair = reverseNode(tmpHead); // 调用此方法后，重复循环了一遍
                if(pair==null){
                    pair=nodePair;
                }else{
                    pair.tail.next=nodePair.head;
                    pair.tail=nodePair.tail;
                }
                tmpHead=nextHead;tmpLast=nextHead;
                i=0;
            }else{
                tmpLast=tmpLast.next;
                i++;
            }
        }
        if(pair==null){
            return tmpHead;
        }
        if(tmpHead!=null){
            pair.tail.next=tmpHead;
        }
        return pair.head;

    }

    /**
     * 解法2
     *      step1：先计算出整个链表的节点个数n  n/k 则代表需要几次的大反转  n%k代表是否有剩余的节点是不需要反转的
     *      step2: 如果反转？ 例如对于节点有 5->4->3->2->1 我们每次都可以将需要的节点放置到第一位 则
     *                                          4->5>3->2->1
     *                                          3->4->5->2->1
     *                                          2->3->4->5->1
     *                                          1->2->3->4->5
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup2(ListNode head,int k){
        return null;
    }


    public NodePair reverseNode(ListNode node){
        ListNode f=node,s=node.next,stageLast=null;
        if(s==null){
            return new NodePair(node,node);
        }
        if(s.next==null){
            s.next=f;
            f.next=null;
            return new NodePair(s,f);
        }
        ListNode t=s.next,res=null;
        for (;f!=null && s!=null ;){
            s.next=f;
            f.next=stageLast;
            stageLast=s;
            if(t==null) break;
            f=t;
            s=t.next;
            if(s!=null) t=s.next;
        }
        if(f!=null && s==null){
            f.next=stageLast;
            return new NodePair(f,node);
        }
        return new NodePair(stageLast,node);
    }

    public static void main(String[] args) {
        ListNode root=new ListNode(1);
        ListNode tmp=root;
        for (int i=2;i<6;i++){
            tmp.next=new ListNode(i);
            tmp=tmp.next;
        }
        ListNode nodePair = new ReverseKGroup().reverseKGroup(root,1);
        System.out.println(nodePair);
    }

    public NodePair reverseNode(ListNode begin,ListNode end){
        ListNode next=end.next;
        NodePair nodePair = reverseNode(begin);
        nodePair.tail.next=next;
        return nodePair;
    }

    static class NodePair{
        ListNode head;
        ListNode tail;
        NodePair(){}
        NodePair(ListNode head,ListNode tail){
            this.head=head;
            this.tail=tail;
        }
    }

}
