package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by wsw on 2019/9/4 15:25
 * 数据流中的中位数
 */
public class N27 {
    int count;
    //PriorityQueue默认为最小堆
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    //重写compare方法，实现最大堆
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(11, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    public void Insert(Integer num) {
        count++;
        if ((count & 1) == 0){
            if (!maxHeap.isEmpty() && num < maxHeap.peek()){
                maxHeap.offer(num);
                num = maxHeap.poll();
            }
            minHeap.offer(num);
        }else {
            if (!minHeap.isEmpty() && num > minHeap.peek()){
                minHeap.offer(num);
                num = minHeap.poll();
            }
            maxHeap.offer(num);
        }
    }

    public Double GetMedian() {
        if (count == 0){
            throw new RuntimeException("no available number!");
        }

        double result;
        //奇数 最大堆的堆顶就是中位数
        if ((count & 1) == 1){
            result = maxHeap.peek();
        }else { //偶数
            result = (minHeap.peek() + maxHeap.peek()) / 2.0;
        }

        return result;
    }

    public static void main(String[] args) {
        N27 n27 = new N27();
        n27.Insert(5);
        n27.Insert(1);
        n27.Insert(2);
        n27.Insert(4);
        n27.Insert(6);
        n27.Insert(3);
        double res = n27.GetMedian();
        System.out.println(res);
    }
}
