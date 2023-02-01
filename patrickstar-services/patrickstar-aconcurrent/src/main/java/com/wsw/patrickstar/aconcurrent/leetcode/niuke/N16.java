package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 从上到下打印二叉树
 *
 */
public class N16 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();

        if (root != null)
            queue.offer(root);

        while (!queue.isEmpty()){
            TreeNode temp = queue.poll();
            list.add(temp.val);

            if (temp.left != null)
                queue.offer(temp.left);

            if (temp.right != null)
                queue.offer(temp.right);
        }

        return list;
    }

    public static void main(String[] args) {
        N16 n16 = new N16();
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(11);
        ArrayList<Integer> resList = n16.PrintFromTopToBottom(root);
        for (int i = 0; i < resList.size(); i++) {
            System.out.print(resList.get(i) + " ");
        }
    }
}
