package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

import java.util.ArrayList;

/**
 *  剑指offer——从尾到头打印链表
 *
 */
public class N1 {
    public static class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (listNode == null)
            return arrayList;
        printListFromTailToHead(listNode,arrayList);
        return arrayList;
    }

    public void printListFromTailToHead(ListNode listNode, ArrayList<Integer> list) {
        if (listNode.next != null)
            printListFromTailToHead(listNode.next,list);
        list.add(listNode.val);
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);

        N1 n = new N1();
        ArrayList<Integer> resList = n.printListFromTailToHead(node);
        for (int i = 0; i < resList.size(); i++) {
            System.out.print(resList.get(i) + " ");
        }
    }
}
