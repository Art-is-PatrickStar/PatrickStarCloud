package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 * 复杂链表的复制
 *
 */
public class N21 {
    public static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null)
            return null;

        RandomListNode currentNode = pHead;
        //第一步：复制每个节点，如复制节点A得到A1，将节点A1插到节点A后面
        while (currentNode != null){
            RandomListNode cloneNode = new RandomListNode(currentNode.label);
            RandomListNode nextNode = currentNode.next;
            currentNode.next = cloneNode;
            cloneNode.next = nextNode;
            currentNode = nextNode;
        }

        currentNode = pHead;
        //第二步：重新遍历链表，复制老节点的随机指针给新节点，例如A1.random = A.random.next
        while (currentNode != null){
            currentNode.next.random = currentNode.random == null? null : currentNode.random.next;
            currentNode = currentNode.next.next;
        }

        //第三步：拆分链表，将链表拆分为原链表和复制后的链表
        currentNode = pHead;
        RandomListNode pCloneNode = pHead.next;
        while (currentNode != null){
            RandomListNode cloneNode = currentNode.next;
            currentNode.next = cloneNode.next;
            cloneNode.next = cloneNode.next == null? null : cloneNode.next.next;
            currentNode = currentNode.next;
        }

        return pCloneNode;
    }

    public static void main(String[] args) {
        RandomListNode node = new RandomListNode(1);
        node.next = new RandomListNode(2);
        node.next.next = new RandomListNode(3);
        node.next.next.next = new RandomListNode(4);
        node.next.next.next.next = new RandomListNode(5);

        node.random = node.next.next;
        node.next.random = node.next.next.next.next;
        node.next.next.next.random = node.next;

        N21 n21 = new N21();
        RandomListNode resNode = n21.Clone(node);
        while (resNode != null){
            System.out.println(resNode.label);
            if (resNode.random != null)
                System.out.println(resNode.random.label);
            System.out.println("--------------");
            resNode = resNode.next;
        }
    }
}
