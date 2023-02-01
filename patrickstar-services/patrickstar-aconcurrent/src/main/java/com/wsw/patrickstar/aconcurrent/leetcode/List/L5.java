package com.wsw.patrickstar.aconcurrent.leetcode.List;

/**
 * 回文链表
 *
 */
public class L5 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        int length = 0;
        ListNode temp = head;
        while (temp != null){
            length++;
            temp = temp.next;
        }
        temp = head;
        int halfLength = length / 2;
        for (int i = 0; i < halfLength; i++) {
            temp = temp.next;
        }
        ListNode Pre = temp;
        ListNode Node = temp.next;
        while (Node != null){
            ListNode Next = Node.next;
            Node.next = Pre;
            Pre = Node;
            Node = Next;
        }
        for (int i = 0; i < halfLength; i++) {
            if (head.val != Pre.val)
                return false;
            head = head.next;
            Pre = Pre.next;
        }
        return true;
    }

    public static void main(String[] args) {
        L5 l = new L5();
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(2);
        node.next.next.next.next.next = new ListNode(1);

        boolean res = l.isPalindrome(node);
        System.out.println(res);
    }
}
