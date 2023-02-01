package com.wsw.patrickstar.aconcurrent.leetcode.Heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 大根堆的实现
 *
 */
public class MaxHeap {
    List<Integer> list;
    int N;

    public MaxHeap(){
        list = new ArrayList<>();
        list.add(1);
        N = 0;
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N > 0;
    }

    public void add(int num){
        list.add(num);
        swim(++N);
    }

    public void swim(int n){
        while (n > 1 && (list.get(n/2) < list.get(n))){
            int max = list.get(n);
            int min = list.get(n/2);
            list.set(n/2, max);
            list.set(n, min);
            n /= 2;
        }
    }

    public int deleteMax(){
        int max = list.get(1);
        int min = list.get(N);
        list.set(1, min);
        list.remove(N--);
        sink(1);
        return max;
    }

    public void sink(int number){
        while (number * 2 <= N){
            int index = number * 2;

            if (index < N && (list.get(index) < list.get(index + 1))){
                index++;
            }

            if (!(list.get(number) < list.get(index))){
                break;
            }

            int max = list.get(index);
            int min = list.get(number);
            list.set(number, max);
            list.set(index, min);
            number = index;
        }
    }

}
