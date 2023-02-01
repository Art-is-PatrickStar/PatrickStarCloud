package com.wsw.patrickstar.aconcurrent.leetcode.List;

/**
 * 删除链表中的重复节点
 *
 * 重复的节点不保留
 *
 */
public class L10 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null)
            return null;

        ListNode node = new ListNode(Integer.MIN_VALUE); //定义链表的一个前驱节点
        node.next = pHead;
        ListNode pre = node;
        ListNode p = pHead;
        boolean deleteMode = false;

        while (p != null){
            if (p.next != null && p.next.val == p.val){
                p.next = p.next.next;
                deleteMode = true;
            }
            else if (deleteMode){
                pre.next = p.next;
                p = pre.next;
                deleteMode = false;
            }
            else{
                pre = p;
                p = p.next;
            }
        }

        return node.next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(3);
        node.next.next.next.next.next = new ListNode(4);
        node.next.next.next.next.next.next = new ListNode(4);
        node.next.next.next.next.next.next.next = new ListNode(5);

        L10 l10 = new L10();
        ListNode res = l10.deleteDuplication(node);

        while (res != null){
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
