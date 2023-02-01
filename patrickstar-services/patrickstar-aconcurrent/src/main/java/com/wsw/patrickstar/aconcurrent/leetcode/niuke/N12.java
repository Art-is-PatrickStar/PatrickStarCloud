package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 * 对称的二叉树
 *
 */
public class N12 {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    boolean isSymmetrical(TreeNode pRoot) {
        return isSymmetrical(pRoot, pRoot);
    }

    private boolean isSymmetrical(TreeNode pRoot1, TreeNode pRoot2){
        if (pRoot1 == null && pRoot2 == null)
            return true;

        if (pRoot1 == null || pRoot2 == null)
            return false;

        if (pRoot1.val != pRoot2.val)
            return false;

        return isSymmetrical(pRoot1.left, pRoot2.right) && isSymmetrical(pRoot1.right, pRoot2.left);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(8);
        node.left = new TreeNode(6);
        node.right = new TreeNode(6);
        node.left.left = new TreeNode(5);
        node.left.right = new TreeNode(7);
        node.right.left = new TreeNode(7);
        node.right.right = new TreeNode(5);

        N12 n12 = new N12();
        System.out.println(n12.isSymmetrical(node));
    }
}
