package com.luff.ltarg.list;

import com.luff.ltarg.common.ListNode;

import java.util.*;

/**
 * @author lsq
 * @date 2020/10/4
 * 2. 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
//        ListNode l1=new ListNode(-1);
//        ListNode l2=new ListNode(-1);
//        ListNode n1=l1,n2=l2;
//        int[] arr1=new int[]{2,4,9,9,9};
//        int[] arr2=new int[]{5,6,9};
//        for (int a1:arr1){
//            n1.next=new ListNode(a1);
//            n1=n1.next;
//        }
//        for (int a2:arr2){
//            n2.next=new ListNode(a2);
//            n2=n2.next;
//        }
//        l1=l1.next;
//        l2=l2.next;
//        ListNode res = new AddTwoNumbers().addTwoNumbers(l1, l2);
//        while (res!=null){
//            System.out.print(res.val+"--->");
//            res=res.next;
//        }


    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return solution1(l1,l2);
    }

    public ListNode solution1(ListNode l1,ListNode l2){
        ListNode header=new ListNode(-1);
        ListNode next=header;
        int up=0,v1=0,v2=0,sum=0;
        while(l1!=null || l2!=null){
            v1=l1==null ? 0 : l1.val;
            v2=l2==null ? 0 : l2.val;
            sum=(v1+v2+up);
            up=sum / 10;
            sum=sum % 10;
            next.next=new ListNode(sum);
            next=next.next;
            l1=l1==null ? null : l1.next;
            l2=l2==null ? null : l2.next;
        }
        if (up!=0){
            next.next=new ListNode(up);
        }
        return header.next;
    }


}
