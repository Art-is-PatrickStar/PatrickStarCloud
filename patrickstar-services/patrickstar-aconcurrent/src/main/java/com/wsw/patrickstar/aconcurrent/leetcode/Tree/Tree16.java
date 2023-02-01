package com.wsw.patrickstar.aconcurrent.leetcode.Tree;

/**
 * 从前序与中序遍历序列构造二叉树
 */
public class Tree16 {
    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            data = val;
        }
    }

    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        if (preOrder == null || inOrder == null || preOrder.length == 0)
            return null;
        return buildCore(preOrder, 0,preOrder.length - 1,inOrder, 0, inOrder.length - 1);
    }

    private TreeNode buildCore(int[] preOrder, int preSt, int preEnd, int[] inorder, int inSt, int inEnd){
        int rootValue = preOrder[preSt]; //前序遍历中的根结点
        TreeNode root = new TreeNode(rootValue);

        if (preSt == preEnd)
            return root;

        //找到中序遍历中的根结点位置
        int rootInorder = inSt;
        while (inorder[rootInorder] != rootValue && rootInorder <= inEnd)
            rootInorder++;

        int leftLength = rootInorder - inSt; //左子树的长度
        int leftPreEnd = preSt + leftLength; //前序序列中左子树的最后一个节点

        // 左子树非空
        if (leftLength > 0){
            root.left = buildCore(preOrder,preSt+1, leftPreEnd, inorder, inSt, rootInorder-1);
        }

        // 右子树非空
        // preEnd 和preSt是前序数组的结尾和开头，相减就是长度，如果左子树长度小于整个长度，那么说明右子树肯定存在。
        // 那么对右子树也进行同样的构建树的操作。
        if (leftLength < preEnd - preSt){
            root.right = buildCore(preOrder, leftPreEnd+1, preEnd, inorder, rootInorder+1, inEnd);
        }

        return root;
    }

    private void inOrder(TreeNode root){
        if (root != null){
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    public static void main(String[] args) {
        Tree16 t = new Tree16();
        int[] preOrder = {3, 9, 20, 15, 7};
        int[] inOrder = {9, 3, 15, 20, 7};
        TreeNode resTree = t.buildTree(preOrder, inOrder);
        t.inOrder(resTree);
    }
}
