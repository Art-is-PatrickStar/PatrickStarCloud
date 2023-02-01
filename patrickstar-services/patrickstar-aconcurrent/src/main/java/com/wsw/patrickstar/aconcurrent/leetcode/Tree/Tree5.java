package com.wsw.patrickstar.aconcurrent.leetcode.Tree;

/**
 *给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 */
public class Tree5
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

    public boolean hasPathSum(TreeNode root, int sum)
    {
        if (root == null)
            return false;

        int flag = sum - root.data;

        if (root.left == null && root.right == null)
            return flag == 0 ? true : false;

        return (hasPathSum(root.left, flag) || hasPathSum(root.right, flag));
    }

    public static void main(String[] args)
    {
        Tree5 t = new Tree5();
        TreeNode p = new TreeNode(5);
        p.left =  new TreeNode(4);
        p.right = new TreeNode(8);
        p.left.left = new TreeNode(11);
        p.right.left = new TreeNode(13);
        p.right.right = new TreeNode(4);
        p.left.left.left = new TreeNode(7);
        p.left.left.right = new TreeNode(2);
        p.right.right.right = new TreeNode(1);

        System.out.println(t.hasPathSum(p, 22));
    }
}
