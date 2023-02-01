package com.wsw.patrickstar.aconcurrent.leetcode.List;

/**
 * 返回链表中倒数第k个节点
 *
 */
public class L11 {
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode FindKthToTail(ListNode head,int k) {
        if (head == null || k <= 0)
            return null;

        ListNode p1 = head;
        ListNode p2 = head;

        for (int i = 0; i < k - 1; i++) {
            if (p2.next != null)
                p2 = p2.next;
            else
                return null;

        }

        while (p2.next != null){
            p2 = p2.next;
            p1 = p1.next;
        }

        return p1;
    }

    public static void main(String[] args) {
        L11 l11 = new L11();
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);

        ListNode res = l11.FindKthToTail(node, 3);
        System.out.println(res.val);
    }
}
