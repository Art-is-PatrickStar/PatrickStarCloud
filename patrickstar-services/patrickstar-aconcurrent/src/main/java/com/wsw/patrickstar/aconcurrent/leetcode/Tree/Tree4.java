package com.wsw.patrickstar.aconcurrent.leetcode.Tree;

/**
 * 判断是否是高度平衡二叉树
 *
 */
public class Tree4
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

    public boolean isBalanced(TreeNode root)
    {
        if (root == null)
            return true;
        int left = Depth(root.left);
        int right = Depth(root.right);
        if (Math.abs(left - right) > 1)
            return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int Depth(TreeNode root)
    {
        if (root == null)
            return 0;
        else
        {
            int nleft = Depth(root.left);
            int nright = Depth(root.right);

            return nleft > nright ? nleft + 1 : nright + 1;
        }
    }

    public static void main(String[] args)
    {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        p.right.left = new TreeNode(4);
        p.right.right = new TreeNode(5);

        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);
        q.right.left = new TreeNode(4);
        q.right.right = new TreeNode(5);
        q.right.left.left = new TreeNode(6);

        Tree4 t = new Tree4();
        System.out.println(t.isBalanced(p));
        System.out.println(t.isBalanced(q));


    }
}
