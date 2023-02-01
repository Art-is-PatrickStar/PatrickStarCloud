package com.wsw.patrickstar.aconcurrent.leetcode.Map.DFS;

import java.util.Stack;

/**
 * 二叉树的DFS深度优先算法
 *
 */
public class DfsWithRecursion {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    //递归实现
    public static void dfsWithRecursion(TreeNode node){
        if (node == null)
            return;

        //在这里处理遍历到的TreeNode节点
        System.out.print(node.val + " ");

        if (node.left != null)
            dfsWithRecursion(node.left);

        if (node.right != null)
            dfsWithRecursion(node.right);
    }

    //DFS的迭代实现版本（Stack）
    public static void dfsWithStack(TreeNode node){
        if (node == null)
            return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()){
            TreeNode temp = stack.pop();

            //在这里处理遍历到的TreeNode节点
            System.out.print(temp.val + " ");

            if (temp.right != null)
                stack.push(temp.right);

            if (temp.left != null)
                stack.push(temp.left);
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right.right = new TreeNode(6);
        dfsWithRecursion(node);
        dfsWithStack(node);
    }
}
