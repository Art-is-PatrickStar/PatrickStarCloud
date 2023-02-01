package com.wsw.patrickstar.aconcurrent.leetcode.Tree;

/**
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。
 * 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 *
 */
public class Tree8
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

    public TreeNode searchBST(TreeNode root, int val)
    {
        if (root == null)
            return null;
        if (root.data == val)
            return root;
        else if (root.data > val)
            return searchBST(root.left, val);
        else
            return searchBST(root.right, val);
    }

    //中序遍历验证输出
    public static void midPrint(TreeNode root)
    {
        if (root != null)
        {
            midPrint(root.left);
            System.out.print(root.data + " ");
            midPrint(root.right);
        }
    }

    public static void main(String[] args)
    {
        TreeNode p = new TreeNode(4);
        p.left = new TreeNode(2);
        p.right = new TreeNode(7);
        p.left.left = new TreeNode(1);
        p.left.right = new TreeNode(3);
        p.left.right.left = new TreeNode(1);

        Tree8 t = new Tree8();
        TreeNode q = t.searchBST(p, 2);

        midPrint(q);

    }
}
