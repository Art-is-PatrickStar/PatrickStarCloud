package com.wsw.patrickstar.aconcurrent.leetcode.List;

/**
 * 环形链表
 *
 */
public class L7
{
    public static class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x)
        {
            val = x;
        }
    }

    public boolean hasCycle(ListNode head)
    {
        if (head == null || head.next == null)
            return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast != null)
        {
            if (slow == fast)
                return true;
            slow = slow.next;
            if (fast.next != null)
                fast = fast.next.next;
            else
                return false;
        }
        return false;
    }

    public static void main(String[] args)
    {
        L7 l = new L7();

        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(2);
        node2.next.next = new ListNode(3);
        node2.next.next.next = new ListNode(4);
        node2.next.next.next.next = node2.next.next;

        boolean res1 = l.hasCycle(node1);
        boolean res2 = l.hasCycle(node2);

        System.out.println(res1);
        System.out.println(res2);
    }
}
