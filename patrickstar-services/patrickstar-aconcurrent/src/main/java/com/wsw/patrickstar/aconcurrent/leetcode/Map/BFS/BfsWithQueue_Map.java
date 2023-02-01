package com.wsw.patrickstar.aconcurrent.leetcode.Map.BFS;

import java.util.*;

/**
 * 图的BFS广度优先算法
 *
 */
public class BfsWithQueue_Map {
    static class Node{
        int val;
        List<Node> children;

        Node(int val, List<Node> children){
            this.val = val;
            this.children = children;
        }
    }

    public static void bfsWithQueue(Node root){
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>(); //Set中存放已被访问过的节点

        if (root != null)
            queue.offer(root);

        while (!queue.isEmpty()){
            Node temp = queue.poll();
            visited.add(temp);

            //在这里处理遍历到的Node节点
            System.out.println(temp.val);

            if (temp.children != null){
                for (Node node : temp.children){
                    if (node != null && !visited.contains(node))
                        queue.offer(node);
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
