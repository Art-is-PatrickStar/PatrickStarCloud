package com.wsw.patrickstar.aconcurrent.leetcode.List;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 */
public class L2
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

    public ListNode getIntersectionNode(ListNode headA, ListNode headB)
    {
        int lengthA = 0;
        int lengthB = 0;

        ListNode tempA = headA;
        ListNode tempB = headB;

        while (tempA != null)
        {
            lengthA++;
            tempA = tempA.next;
        }

        while (tempB != null)
        {
            lengthB++;
            tempB = tempB.next;
        }

        tempA = headA;
        tempB = headB;

        if (lengthB > lengthA)
        {
            int DV = lengthB - lengthA;
            for (int i = 0; i < DV && tempB != null; i++)
            {
                tempB = tempB.next;
            }
        }
        else
        {
            int DV = lengthA - lengthB;
            for (int i = 0; i < DV && tempA != null; i++)
            {
                tempA = tempA.next;
            }
        }

        while (tempA != null && tempB != null && tempA.val != tempB.val)
        {
            tempA = tempA.next;
            tempB = tempB.next;
        }

        return tempA;
    }

    public static void main(String[] args)
    {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(3);
        node1.next.next = new ListNode(4);
        node1.next.next.next = new ListNode(7);
        node1.next.next.next.next = new ListNode(9);

        ListNode node2 = new ListNode(5);
        node2.next = new ListNode(7);
        node2.next.next = new ListNode(9);

        L2 l = new L2();
        ListNode node = l.getIntersectionNode(node1, node2);
        try
        {
            System.out.println(node.val);
        }catch (Exception e)
        {
            System.out.println(e);
        }

    }
}
