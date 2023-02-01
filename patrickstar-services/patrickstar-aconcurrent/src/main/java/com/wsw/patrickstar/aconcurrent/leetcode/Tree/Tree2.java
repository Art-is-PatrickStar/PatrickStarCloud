package com.wsw.patrickstar.aconcurrent.leetcode.Tree;

/**
 *给定两个二叉树，编写一个函数来检验它们是否相同。
 *
 *如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 */
public class Tree2
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

    public boolean isSameTree(TreeNode p, TreeNode q)
    {
        if (p == null && q == null)
            return true;
        else if (p == null || q == null)
            return false;
        else
            return (p.data == q.data && isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }

    public static void main(String[] args)
    {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = null;

        TreeNode q = new TreeNode(1);
        q.left = null;
        q.right = new TreeNode(3);

        Tree2 t = new Tree2();
        System.out.println(t.isSameTree(p, q));
    }
}
