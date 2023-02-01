package com.wsw.patrickstar.aconcurrent.leetcode.List;

/**
 * 删除链表中的元素
 *
 */
public class L6
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

    public ListNode removeElements(ListNode head, int val)
    {
        if (head == null)
            return head;
        while (head != null && head.val == val)
            head = head.next;
        if (head == null)
            return head;
        ListNode Pre = head;
        ListNode temp = head.next;
        while (temp != null)
        {
            if (temp.next == null)
            {
                if (temp.val != val)
                    break;
                Pre.next = null;
                break;
            }
            else if (temp != null && temp.val == val)
            {
                int value = temp.next.val;
                temp.val = value;
                temp.next = temp.next.next;
            }
            else
            {
                Pre = temp;
                temp = temp.next;
            }
        }
        return head;
    }

    public static void main(String[] args)
    {
        L6 l = new L6();
        ListNode node = new ListNode(1);
        node.next = new ListNode(4);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(4);

        ListNode res = l.removeElements(node,4);

        while (res != null)
        {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
