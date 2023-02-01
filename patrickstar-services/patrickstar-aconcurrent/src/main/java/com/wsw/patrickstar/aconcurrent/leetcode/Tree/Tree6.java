package com.wsw.patrickstar.aconcurrent.leetcode.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 */
public class Tree6
{
    public static class TreeNode
    {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode(int val)
        {
            data = val;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum)
    {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        if (root == null)
            return list;

        getPass(list,temp,sum,0,root);

        return list;

    }

    public void getPass(List<List<Integer>>list, List<Integer>temp, int sum, int target,TreeNode root)
    {
        if (root.left == null && root.right == null)
        {
            if (target + root.data == sum)
            {
                temp.add(root.data);
                list.add(new ArrayList<Integer>(temp));
                temp.remove(temp.size() - 1);
            }
        }

        else
        {
            if (root.left != null)
            {
                temp.add(root.data);
                getPass(list,temp,sum,target+root.data,root.left);
                temp.remove(temp.size() - 1);
            }

            if (root.right != null)
            {
                temp.add(root.data);
                getPass(list,temp,sum,target+root.data,root.right);
                temp.remove(temp.size() - 1);
            }
        }

    }

    public static void main(String[] args)
    {
        TreeNode p = new TreeNode(5);
        p.left = new TreeNode(4);
        p.right = new TreeNode(8);
        p.left.left = new TreeNode(11);
        p.right.left = new TreeNode(13);
        p.right.right = new TreeNode(4);
        p.left.left.left = new TreeNode(7);
        p.left.left.right = new TreeNode(2);
        p.right.right.left = new TreeNode(5);
        p.right.right.right = new TreeNode(1);

        Tree6 t = new Tree6();

        List<List<Integer>> list = t.pathSum(p,22);
        for (int i = 0; i < list.size(); i++)
        {
            System.out.print(list.get(i) + " ");
        }
    }

}
