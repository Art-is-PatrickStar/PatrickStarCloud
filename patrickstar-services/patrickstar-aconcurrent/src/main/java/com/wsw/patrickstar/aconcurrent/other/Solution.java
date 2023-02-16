package com.wsw.patrickstar.aconcurrent.other;

import java.util.Scanner;

/**
 * @Author: wangsongwen
 * @Date: 2022/4/13 15:15
 * @Description:
 */

class Solution {
    public static void main(String[] args) {
        // Write your code here
        //Scanner
        Scanner s = new Scanner(System.in);
        String s1 = s.nextLine();             // Read input from STDIN
        String[] t1 = s1.split(" ");
        StringBuilder end = new StringBuilder();
        for (int i = 0; i < Integer.parseInt(t1[0]); i++) {
            int max = 0;
            String s2 = s.nextLine();
            String[] t2 = s2.split(" ");
            for (int j = 0; j < Integer.parseInt(t1[1]); j++) {
                if (t2[j].length() > max) {
                    max = t2[j].length();
                }
            }
            if (i != Integer.parseInt(t1[0]) - 1) {
                end.append(max).append(" ");
            } else {
                end.append(max);
            }
        }
        System.out.println(end);    // Write output to STDOUT
    }
}