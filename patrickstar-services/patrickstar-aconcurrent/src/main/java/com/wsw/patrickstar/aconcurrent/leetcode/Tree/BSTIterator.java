package com.wsw.patrickstar.aconcurrent.leetcode.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉搜索树迭代器
 * 使用二叉搜索树的根节点初始化迭代器。
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 */
public class BSTIterator {
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x){
          val = x;
      }
    }

    Queue<Integer> queue = new LinkedList<>();

    public BSTIterator(TreeNode root) {
        inOrder(root);
    }

    public void inOrder(TreeNode root){
        if (root == null)
            return;
        inOrder(root.left);
        queue.offer(root.val);
        inOrder(root.right);
    }

    /** @return the next smallest number */
    public int next() {
        return queue.poll();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(7);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(15);
        treeNode.right.left = new TreeNode(9);
        treeNode.right.right = new TreeNode(20);

        BSTIterator bstIterator = new BSTIterator(treeNode);
        System.out.println(bstIterator.next());
        System.out.println(bstIterator.next());
        System.out.println(bstIterator.hasNext());
        System.out.println(bstIterator.next());
        System.out.println(bstIterator.next());
        System.out.println(bstIterator.hasNext());
        System.out.println(bstIterator.next());
        System.out.println(bstIterator.hasNext());
    }
}
