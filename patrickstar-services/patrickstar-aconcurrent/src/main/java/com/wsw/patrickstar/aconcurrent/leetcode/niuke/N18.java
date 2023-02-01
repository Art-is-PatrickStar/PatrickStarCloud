package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 之字形打印二叉树
 *
 */
public class N18 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> resList = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        if (pRoot != null)
            stack1.push(pRoot);

        while (!stack1.isEmpty() || !stack2.isEmpty()){
            if (!stack1.isEmpty()){
                ArrayList<Integer> arrayList = new ArrayList<>();
                while (!stack1.isEmpty()){
                    TreeNode tempNode = stack1.pop();
                    arrayList.add(tempNode.val);
                    if (tempNode.left != null)
                        stack2.push(tempNode.left);
                    if (tempNode.right != null)
                        stack2.push(tempNode.right);
                }
                resList.add(arrayList);
            }
            else {
                ArrayList<Integer> arrayList = new ArrayList<>();
                while (!stack2.isEmpty()){
                    TreeNode tempNode = stack2.pop();
                    arrayList.add(tempNode.val);
                    if (tempNode.right != null)
                        stack1.push(tempNode.right);
                    if (tempNode.left != null)
                        stack1.push(tempNode.left);
                }
                resList.add(arrayList);
            }
        }

        return resList;
    }

    public static void main(String[] args) {
        N18 n18 = new N18();
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(11);
        ArrayList<ArrayList<Integer>> resList = n18.Print(root);
        for (int i = 0; i < resList.size(); i++) {
            for (int j = 0; j < resList.get(i).size(); j++) {
                System.out.print(resList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
