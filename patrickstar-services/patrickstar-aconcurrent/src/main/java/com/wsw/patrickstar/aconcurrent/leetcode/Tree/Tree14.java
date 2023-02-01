package com.wsw.patrickstar.aconcurrent.leetcode.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的后序遍历
 *
 */
public class Tree14
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
    public List<Integer> postorderTraversal(TreeNode root)
    {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        if (root == null)
            return list;

        stack.push(root);

        while (!stack.isEmpty())
        {
            TreeNode node = stack.pop();

            list.add(0,node.data);

            if (node.left != null)
                stack.push(node.left);

            if (node.right != null)
                stack.push(node.right);

        }

        return list;
    }

    /**
     *递归
     */
//    public List<Integer> postorderTraversal(TreeNode root)
//    {
//        List<Integer> list = new ArrayList<>();
//
//        if (root == null)
//            return list;
//
//        postorder(root,list);
//
//        return list;
//    }
//
//    public static void postorder(TreeNode root, List list)
//    {
//        if (root != null)
//        {
//            postorder(root.left,list);
//            postorder(root.right,list);
//            list.add(root.data);
//        }
//    }

    public static void main(String[] args)
    {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        p.left.left = new TreeNode(4);
        p.left.right = new TreeNode(5);
        p.right.right = new TreeNode(6);

        Tree14 t = new Tree14();

        List<Integer> list = t.postorderTraversal(p);
        for (int i = 0; i < list.size(); i++)
        {
            System.out.print(list.get(i) + " ");
        }

    }

}
