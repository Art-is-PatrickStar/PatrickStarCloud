package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 分行从上到下打印二叉树
 *
 */
public class N17 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> resList = new ArrayList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<TreeNode> linkedList = new LinkedList<>();

        if (pRoot != null)
            linkedList.add(pRoot);

        int now = 1;  //记录当前层中还没有打印的节点数
        int next = 0; //记录下一层中的节点数

        while (!linkedList.isEmpty()){
            TreeNode tempNode = linkedList.remove();
            now--;
            arrayList.add(tempNode.val);

            if (tempNode.left != null){
                linkedList.add(tempNode.left);
                next++;
            }

            if (tempNode.right != null){
                linkedList.add(tempNode.right);
                next++;
            }

            if (now == 0){
                resList.add(new ArrayList<>(arrayList));
                arrayList.clear();
                now = next;
                next = 0;
            }
        }

        return resList;
    }

    public static void main(String[] args) {
        N17 n17 = new N17();
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(11);
        ArrayList<ArrayList<Integer>> resList = n17.Print(root);
        for (int i = 0; i < resList.size(); i++) {
            for (int j = 0; j < resList.get(i).size(); j++) {
                System.out.print(resList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
