package com.wsw.patrickstar.aconcurrent.leetcode.List;

import java.util.HashMap;

/**
 * 环形链表II——寻找环形链表的入口
 *
 */
public class L9
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
    //使用额外的空间---HashMap
    public ListNode detectCycle(ListNode head)
    {
        HashMap<ListNode,Integer> map = new HashMap<>();
        if (head == null || head.next == null)
            return null;
        while (head != null)
        {
            if (map.containsKey(head) == false)
            {
                map.put(head,1);
                head = head.next;
            }
            else
                return head;
        }
        return null;
    }

    public static void main(String[] args)
    {
        L9 l = new L9();
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = node.next;

        ListNode res = l.detectCycle(node);
        System.out.println(res.val);
    }
}
