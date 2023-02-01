package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 * 树的子结构
 *
 */
public class N10 {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root2 == null)
            return false;

        if (root1 == null && root2 != null)
            return false;

        boolean flag = false;
        if (root1.val == root2.val)
            flag = doHasSubtree(root1, root2);

        if (!flag){
            flag = HasSubtree(root1.left, root2);
            if (!flag)
                flag = HasSubtree(root1.right, root2);
        }

        return flag;
    }

    private boolean doHasSubtree(TreeNode root1, TreeNode root2){
        if (root2 == null)
            return true;

        if (root1 == null && root2 != null)
            return false;

        if (root1.val == root2.val)
            return doHasSubtree(root1.left, root2.left) && doHasSubtree(root1.right, root2.right);
        else
            return false;
    }

    public static void main(String[] args) {
        TreeNode tree1 = new TreeNode(8);
        tree1.left = new TreeNode(8);
        tree1.right = new TreeNode(7);
        tree1.left.left = new TreeNode(9);
        tree1.left.right = new TreeNode(2);
        tree1.left.right.left = new TreeNode(4);
        tree1.left.right.right = new TreeNode(7);
        TreeNode tree2 = new TreeNode(8);
        tree2.left = new TreeNode(9);
        tree2.right = new TreeNode(2);

        N10 n10 = new N10();
        System.out.println(n10.HasSubtree(tree1, tree2));
    }
}
