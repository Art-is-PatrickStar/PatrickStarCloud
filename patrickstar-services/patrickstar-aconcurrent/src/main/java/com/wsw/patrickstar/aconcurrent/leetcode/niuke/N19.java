package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 * 二叉搜索树的后序遍历序列
 *
 */
public class N19 {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length == 0)
            return false;
        return isTreeBSF(sequence, 0, sequence.length - 1);
    }

    private boolean isTreeBSF(int[] sequence, int start, int end) {
        if (start >= end)
            return true;

        int i = start;
        for (; i < end; i++) {
            if (sequence[i] > sequence[end])
                break;
        }

        for (int j = i; j < end; j++) {
            if (sequence[j] < sequence[end])
                return false;
        }

        return isTreeBSF(sequence, start, i-1) && isTreeBSF(sequence, i+1, end-1);
    }

    public static void main(String[] args) {
        N19 n19 = new N19();
        int[] a = {5,7,6,9,11,10,8};
        boolean res = n19.VerifySquenceOfBST(a);
        System.out.println(res);
    }
}
