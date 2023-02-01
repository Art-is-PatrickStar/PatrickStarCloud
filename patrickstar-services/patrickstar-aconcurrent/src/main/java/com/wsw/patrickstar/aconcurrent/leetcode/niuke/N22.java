package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 * 二叉搜索树和双向链表
 *
 */
public class N22 {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    TreeNode tail = null;
    TreeNode realHead = null;

    public TreeNode Convert(TreeNode pRootOfTree) {
        ConvertSub(pRootOfTree);
        return realHead;
    }

    private void ConvertSub(TreeNode pRootOfTree) {
        if (pRootOfTree == null)
            return;

        ConvertSub(pRootOfTree.left);
        if (tail == null){
            tail = pRootOfTree;
            realHead = pRootOfTree;
        }else {
            tail.right = pRootOfTree;
            pRootOfTree.left = tail;
            tail = pRootOfTree;
        }
        ConvertSub(pRootOfTree.right);
    }

    public static void main(String[] args) {

    }
}
