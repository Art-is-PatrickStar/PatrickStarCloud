package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

import java.util.ArrayList;

/**
 * 二叉树中和为某一值的路径
 */
public class N20 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> resList = new ArrayList<>();
        ArrayList<Integer> nodeList = new ArrayList<>();

        if (root == null)
            return resList;

        int sum = 0;
        path(root, target, resList, nodeList, sum);
        return resList;
    }

    private void path(TreeNode root, int target, ArrayList<ArrayList<Integer>> resList, ArrayList<Integer> nodeList, int sum) {
        if (root == null)
            return;

        sum += root.val;
        if (root.left == null && root.right == null){
            if (sum == target){
                nodeList.add(root.val);
                resList.add(new ArrayList<>(nodeList));
                nodeList.remove(nodeList.size() - 1);
            }
            return;
        }

        nodeList.add(root.val);
        path(root.left, target, resList, nodeList, sum);
        path(root.right, target, resList, nodeList, sum);
        nodeList.remove(nodeList.size() - 1);
    }

    public static void main(String[] args) {
        N20 n20 = new N20();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        ArrayList<ArrayList<Integer>> resList = n20.FindPath(root, 22);
        for (int i = 0; i < resList.size(); i++) {
            for (int j = 0; j < resList.get(i).size(); j++) {
                System.out.print(resList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
