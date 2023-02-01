package com.wsw.patrickstar.aconcurrent.leetcode.Tree;

/**
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 */
public class Tree7
{
    public static class TreeNode
    {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode(int val)
        {
            data = val;
        }
    }

    public int minDepth(TreeNode root)
    {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        if (root.left == null)
            return minDepth(root.right) + 1;
        if (root.right == null)
            return minDepth(root.left) + 1;

        return Math.min(minDepth(root.left) + 1, minDepth(root.right) + 1);
    }


    public static void main(String[] args)
    {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        p.right.left = new TreeNode(4);
        p.right.right = new TreeNode(5);

        Tree7 t = new Tree7();
        System.out.println(t.minDepth(p));
    }
}
