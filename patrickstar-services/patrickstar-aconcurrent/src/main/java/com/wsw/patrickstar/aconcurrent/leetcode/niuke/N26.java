package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * 最小的k个数
 *
 */
public class N26 {
    //全排序
    /*public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || k > input.length)
            return list;

        Arrays.sort(input);
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }*/

    //最大堆
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> resList = new ArrayList<>();
        if (input == null || k > input.length || k == 0)
            return resList;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for (int i = 0; i < input.length; i++) {
            if (maxHeap.size() < k){
                maxHeap.offer(input[i]);
            }
            else if (maxHeap.peek() > input[i]){
                maxHeap.poll();
                maxHeap.offer(input[i]);
            }
        }

        resList.addAll(maxHeap);
        return resList;
    }

    public static void main(String[] args) {
        N26 n26 = new N26();
        int[] input = {4, 5, 1, 6, 2, 7, 3, 8};
        ArrayList<Integer> resList = n26.GetLeastNumbers_Solution(input, 4);
        for (Integer integer : resList) {
            System.out.print(integer + " ");
        }
    }
}
