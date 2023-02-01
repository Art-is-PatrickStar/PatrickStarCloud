package com.wsw.patrickstar.aconcurrent.leetcode.qiuzhao.qianxin;

import java.util.Scanner;

/**
 * Created by wsw on 2019/9/9 20:20
 * 奇安信笔试第二题: 平衡二叉树节点的公共祖先
 *
 * 利用数组就可以解决
 */
public class Main1 {
    public static void main(String[] args) {
        //处理输入数据
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); //树的层数
        int len = (int) (Math.pow(2, n) - 1); //数的节点数
        int[] nodes = new int[len]; //存储节点
        scanner.nextLine();//换行
        for (int i = 0; i < len; i++) {
            nodes[i] = scanner.nextInt();
        }
        scanner.nextLine();
        int node1 = scanner.nextInt();
        int node2 = scanner.nextInt();
        scanner.close();

        //利用满二叉树的性质，借助数组下标关系实现
        int index1 = -1;
        int index2 = -1;
        //找到两个目标节点在数组中的下标
        for (int i = 0; i < len; i++) {
            if (nodes[i] == node1)
                index1 = i;
            if (nodes[i] == node2)
                index2 = i;
        }
        //目标节点不存在
        if (index1 == -1 || index2 == -1){
            System.out.println(-1);
        }
        //下标除以2找到最近的公共祖先
        while (index1 != index2){
            if (index1 > index2){
                index1 = (index1 - 1) / 2;
            }else {
                index2 = (index2 - 1) / 2;
            }
        }
        System.out.println(nodes[index1]);
    }
}
