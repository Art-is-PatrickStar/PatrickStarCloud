package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 * 序列化和反序列化二叉树
 *
 */
public class N23 {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    String Serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null){
            sb.append("#,");
            return sb.toString();
        }
        sb.append(root.val + ",");
        sb.append(Serialize(root.left));
        sb.append(Serialize(root.right));
        return sb.toString();
    }

    int index = -1;

    TreeNode Deserialize(String str) {
        index++;
        String[] s = str.split(",");
        TreeNode node = null;

        if (!s[index].equals("#")){
            node = new TreeNode(Integer.valueOf(s[index]));
            node.left = Deserialize(str);
            node.right = Deserialize(str);
        }
        return node;
    }

    //利用中序遍历来输出二叉树
    private static void midOrder(TreeNode node){
        if (node != null){
            midOrder(node.left);
            System.out.println(node.val);
            midOrder(node.right);
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.right.left = new TreeNode(5);
        node.right.right = new TreeNode(6);

        N23 n23 = new N23();
        String res1 = n23.Serialize(node);
        System.out.println(res1);
        TreeNode res2 = n23.Deserialize(res1);
        midOrder(res2);
    }
}
