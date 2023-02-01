package com.wsw.patrickstar.aconcurrent.leetcode.List;

/**
 * 删除链表中的节点
 *
 */
public class L1 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public void deleteNode(ListNode head, ListNode node) {
        //参数检验
        if (head == null || node == null)
            return;
        //链表中只有一个节点
        if (head == node && head.next == null)
            head = null;
        else{
            //删除节点为尾节点
            if (node.next == null){
                ListNode temp = head;
                while (temp.next != node)
                    temp = temp.next;
                temp.next = null;
            }
            //删除头节点和中间节点
            else {
                node.val = node.next.val;
                node.next = node.next.next;
            }
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(1);
        node1.next.next.next = new ListNode(5);

        L1 l = new L1();
        l.deleteNode(node1, node1.next);

        while (node1 != null) {
            System.out.print(node1.val + " ");
            node1 = node1.next;
        }

    }
}
