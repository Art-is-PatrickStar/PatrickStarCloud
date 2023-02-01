package com.wsw.patrickstar.aconcurrent.leetcode.Map.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的BFS广度优先算法
 *
 */
public class BfsWithQueue {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public static void bfsWithQueue(TreeNode node){
        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        if(node != null){
            queue.offer(node);
        }

        while (!queue.isEmpty()){
            TreeNode temp = queue.poll();

            //在这里处理遍历到的TreeNode节点
            System.out.print(temp.val + " ");

            if (temp.left != null){
                queue.offer(temp.left);
            }

            if (temp.right != null){
                queue.offer(temp.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right.right = new TreeNode(6);
        bfsWithQueue(node);
    }
}
