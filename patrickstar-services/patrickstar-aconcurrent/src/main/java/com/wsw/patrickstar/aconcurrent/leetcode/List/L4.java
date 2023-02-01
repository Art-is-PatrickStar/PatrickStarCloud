package com.wsw.patrickstar.aconcurrent.leetcode.List;

/**
 * 反转链表
 * 从尾到头打印链表
 *
 */
public class L4 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode Pre = head;
        ListNode Node = head.next;
        Pre.next = null;

        while (Node != null){
            ListNode Next = Node.next;
            Node.next = Pre;
            Pre = Node;
            Node = Next;
        }

        return Pre;
    }

    //用栈实现
    /*public void printListFromTailToHead(ListNode node){
        Stack<ListNode> stack = new Stack<>();

        ListNode listNode = node;
        while (listNode != null){
            stack.push(listNode);
            listNode = listNode.next;
        }

        while (!stack.isEmpty()){
            listNode = stack.pop();
            System.out.print(listNode.val + " ");
        }
    }*/

    //用递归实现
    /*public ArrayList<Integer> printListFromTailToHead(ListNode node){
        ArrayList<Integer> arrayList = new ArrayList<>();

        if (node == null)
            return arrayList;

        printListFromTailToHead(node, arrayList);

        return arrayList;
    }

    public void printListFromTailToHead(ListNode node, ArrayList<Integer> arrayList){
        if (node.next != null){
            printListFromTailToHead(node.next, arrayList);
        }
        arrayList.add(node.val);
    }*/

    public static void main(String[] args) {
        L4 l = new L4();
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);

        /*ArrayList<Integer> arrayList = l.printListFromTailToHead(node);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " ");
        }*/

        //l.printListFromTailToHead(node);

        ListNode res = l.reverseList(node);

        while (res != null){
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
