package com.wsw.patrickstar.aconcurrent.leetcode.List;

/**
 * 合并两个有序链表
 *
 */
public class L3 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode temp = new ListNode(0);
        ListNode res = temp;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val){
                temp.next = list1;
                list1 = list1.next;
                temp = temp.next;
            }
            else {
                temp.next = list2;
                list2 = list2.next;
                temp = temp.next;
            }
        }

        if (list1 == null){
            temp.next = list2;
        }

        if (list2 == null){
            temp.next = list1;
        }

        return res.next;
    }

    public static void main(String[] args) {
        L3 l = new L3();

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode res = l.mergeTwoLists(l1,l2);

        while (res != null){
            System.out.print(res.val + " ");
            res = res.next;
        }

    }
}
