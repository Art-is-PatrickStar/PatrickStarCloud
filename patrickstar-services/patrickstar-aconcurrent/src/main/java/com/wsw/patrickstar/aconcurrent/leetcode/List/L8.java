package com.wsw.patrickstar.aconcurrent.leetcode.List;

/**
 * 删除链表的倒数第n个节点
 *
 */
public class L8 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        //如果是删除最后一个节点，就遍历删除
        if (n == 1) {
            if (head == null || head.next == null)
                return null;
            ListNode Pre = head;
            ListNode temp = head.next;
            while (temp.next != null) {
                temp = temp.next;
                Pre = Pre.next;
            }
            Pre.next = null;
            return head;
        }
        int k = n - 1;
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.val = slow.next.val;
        slow.next = slow.next.next;
        return head;
    }

    public static void main(String[] args) {
        L8 l = new L8();
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);

        ListNode res = l.removeNthFromEnd(node,2);

        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
