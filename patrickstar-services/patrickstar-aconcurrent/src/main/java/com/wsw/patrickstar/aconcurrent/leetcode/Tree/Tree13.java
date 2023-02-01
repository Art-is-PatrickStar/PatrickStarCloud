package com.wsw.patrickstar.aconcurrent.leetcode.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历
 *
 */
public class Tree13
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

    /**
     *非递归
     */
    public List<Integer> inorderTraversal(TreeNode root)
    {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty())
        {
            while (root != null)
            {
                stack.push(root);
                root = root.left;
            }

            if (!stack.isEmpty())
            {
                list.add(stack.peek().data);
                root = stack.peek().right;
                stack.pop();
            }
        }

        return list;
    }

    /**
     * 递归
     */
//    public List<Integer> inorderTraversal(TreeNode root)
//    {
//        List<Integer> list = new ArrayList<>();
//
//        if (root == null)
//            return list;
//
//        inorder(root,list);
//
//        return list;
//    }
//
//    public static void inorder(TreeNode root, List list)
//    {
//        if (root != null)
//        {
//            inorder(root.left,list);
//            list.add(root.data);
//            inorder(root.right,list);
//        }
//
//    }

    public static void main(String[] args)
    {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        p.left.left = new TreeNode(4);
        p.left.right = new TreeNode(5);
        p.right.right = new TreeNode(6);

        Tree13 t = new Tree13();

        List<Integer> list = t.inorderTraversal(p);
        for (int i = 0; i < list.size(); i++)
        {
            System.out.print(list.get(i) + " ");
        }

    }

}
