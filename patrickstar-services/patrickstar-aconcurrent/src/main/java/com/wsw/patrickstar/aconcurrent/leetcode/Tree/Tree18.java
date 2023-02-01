package com.wsw.patrickstar.aconcurrent.leetcode.Tree;

/**
 * 二叉树的下一个节点
 * 给定一棵二叉树和其中的一个节点，找出中序遍历序列的下一个节点
 *
 */
public class Tree18 {
    public static class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode){
        if (pNode == null)
            return null;

        //节点的右子树非空，中序遍历的下一个节点就是右子树的最左叶子节点
        if (pNode.right != null){
            TreeLinkNode temp = pNode.right;
            while (temp.left != null){
                temp = temp.left;
            }
            return temp;
        }

        TreeLinkNode temp = pNode.next; //父节点
        TreeLinkNode pre = pNode; //当前节点
        while (temp != null){
            if (temp.left == pre)
                return temp;
            pre = temp;
            temp = temp.next;
        }
        return temp;
    }

    public static void main(String[] args) {
        Tree18 tree18 = new Tree18();

        TreeLinkNode pNode = new TreeLinkNode(1);
        pNode.left = new TreeLinkNode(2);
        pNode.right = new TreeLinkNode(3);
        pNode.left.left = new TreeLinkNode(4);
        pNode.left.right = new TreeLinkNode(5);
        pNode.left.right.left = new TreeLinkNode(8);
        pNode.left.right.right = new TreeLinkNode(9);
        pNode.right.left = new TreeLinkNode(6);
        pNode.right.right = new TreeLinkNode(7);
        pNode.next = null;
        pNode.left.next = pNode;
        pNode.right.next = pNode;
        pNode.left.left.next = pNode.left;
        pNode.left.right.next = pNode.left;
        pNode.left.right.left.next = pNode.left.right;
        pNode.left.right.right.next = pNode.left.right;
        pNode.right.left.next = pNode.right;
        pNode.right.right.next = pNode.right;

        System.out.println(tree18.GetNext(pNode).val);
        System.out.println(tree18.GetNext(pNode.left.left).val);
        System.out.println(tree18.GetNext(pNode.left.right.right).val);
        System.out.println(tree18.GetNext(pNode.right.right));
    }
}
