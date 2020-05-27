package com.luff.ltarg.twoPointer;

import com.luff.ltarg.common.ListNode;

public class DetectCycle {

    /**
     * 检测环，给定一个链表，返回链表开始入环的第一个节点，如果链表五环，则返回null
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if(head==null) return null;
        ListNode intersect = getIntersect(head);
        if(intersect==null) return null;
        ListNode node1=head,node2=intersect;
        while(node1!=node2){
            node1=node1.next;
            node2=node2.next;
        }
        return node1;
    }

    public ListNode getIntersect(ListNode head){
        ListNode slow=head,fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return fast;
            }
        }
        return null;
    }
}
