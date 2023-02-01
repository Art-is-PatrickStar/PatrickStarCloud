package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 * 二叉树的镜像
 *
 */
public class N11 {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public void Mirror(TreeNode root) {
        if (root == null)
            return;

        if (root.left == null && root.right == null)
            return;

        TreeNode tempNode = root.left;
        root.left = root.right;
        root.right = tempNode;

        if (root.left != null)
            Mirror(root.left);

        if (root.right != null)
            Mirror(root.right);
    }

    //中序遍历验证结果
    private static void midSearch(TreeNode node){
        if (node != null){
            midSearch(node.left);
            System.out.print(node.val + " ");
            midSearch(node.right);
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(8);
        node.left = new TreeNode(6);
        node.right = new TreeNode(10);
        node.left.left = new TreeNode(5);
        node.left.right = new TreeNode(7);
        node.right.left = new TreeNode(9);
        node.right.right = new TreeNode(11);

        N11 n11 = new N11();
        n11.Mirror(node);
        midSearch(node);
    }
}
