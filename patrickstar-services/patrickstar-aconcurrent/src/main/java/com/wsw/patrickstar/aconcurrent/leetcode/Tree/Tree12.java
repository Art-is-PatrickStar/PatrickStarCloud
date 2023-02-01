package com.wsw.patrickstar.aconcurrent.leetcode.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的前序遍历
 *
 */
public class Tree12
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
     * 非递归
     */
    public List<Integer> preorderTraversal(TreeNode root)
    {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        if (root == null)
            return list;

        TreeNode node = root;

        while (node != null)
        {
            list.add(node.data);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
            {
                node = node.left;
            }
            else
            {
                if (stack.isEmpty())
                    break;
                else
                    node = stack.pop();
            }
        }

        return list;
    }

    /**
     *递归
     */
//    public List<Integer> preorderTraversal(TreeNode root)
//    {
//        List<Integer> list = new ArrayList<>();
//
//        if (root == null)
//            return list;
//
//        preorder(root,list);
//
//        return list;
//    }
//
//    public static void preorder(TreeNode root, List list)
//    {
//        if (root != null)
//        {
//            list.add(root.data);
//            preorder(root.left,list);
//            preorder(root.right,list);
//        }
//    }

    public static void main(String[] args)
    {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        p.left.left = new TreeNode(4);

        Tree12 t = new Tree12();

        List<Integer> list = t.preorderTraversal(p);
        for (int i = 0; i < list.size(); i++)
        {
            System.out.print(list.get(i) + " ");
        }

    }
}
