package com.wsw.patrickstar.aconcurrent.leetcode.Stack_queue;

import java.util.TreeMap;

/**
 * leetcode853 车队
 *
 */
public class T12 {
    public int carFleet(int target, int[] position, int[] speed) {
        TreeMap<Integer, Double> treeMap = new TreeMap<>();

        for (int i = 0; i < position.length; i++) {
            treeMap.put(-position[i], (double)(target - position[i])/speed[i]);
        }
        //System.out.println(treeMap.keySet());
        //System.out.println(treeMap.values());
        int res = 0;
        double temp = 0;
        for (Double d : treeMap.values()){
            if (d > temp){
                temp = d;
                res++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        T12 t = new T12();
        System.out.println(t.carFleet(12, new int[]{10,8,0,5,3}, new int[]{2,4,1,1,3}));
    }
}
