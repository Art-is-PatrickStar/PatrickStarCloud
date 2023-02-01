package com.wsw.patrickstar.aconcurrent.leetcode.List;

/**
 * 链表中环的入口节点
 *
 */
public class L12 {
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null)
            return null;

        ListNode p1 = pHead;
        ListNode p2 = pHead;

        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;

            if (p1 == p2){
                p1 = pHead;
                while (p1 != p2){
                    p1 = p1.next;
                    p2 = p2.next;
                }
                if (p1 == p2)
                    return p1;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        L12 l12 = new L12();
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next = new ListNode(6);
        node.next.next.next.next.next.next = node.next.next;

        ListNode res = l12.EntryNodeOfLoop(node);
        System.out.println(res.val);
    }
}
