package com.luff.ltarg.twoPointer;

import com.luff.ltarg.common.ListNode;

public class HasCycle {


    /**
     * 给定一个链表，判断链表中是否有环
     *     指针slow每次只走一步，指针fast每次走两步，如果给定链表有环，则必定出现fast=slow,如果没有环fast==null || fast.next==null
     *
     *     证明：
     *          无环的情况下：略
     *          有环的情况下：
     *              因为fast指针走的更快，所以必定在某刻先于slow指针走完链表，并且到达环的起始点，当fast指针到达起始点a时，
     *              有两种情况，
     *                i:slow走在fast指针的前面
     *                ii:slow依然在fast指针的后面
     *
     *               对于情况ii,因为slow指针在后面，而fast指针时快指针，并且在前面，因此经过一段时间，fast指针必然在slow指针后面
     *               回到情况i中
     *
     *               i:
     *                  i1：当fast落后一步于slow指针，则再走一步，两个指针走到相同节点
     *                  i2：当fast落后两步于slow指针，slow先走一步时后，fast走两步，回到i1情况
     *                  i3: 同理，
     *                  因此只要当fast指针在slow指针后面，fast指针必定追到slow指针
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow=head,fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return true;
            }
        }
        return false;

    }
}
