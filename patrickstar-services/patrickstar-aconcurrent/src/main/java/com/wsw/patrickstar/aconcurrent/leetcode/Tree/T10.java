package com.wsw.patrickstar.aconcurrent.leetcode.Tree;

/**
 * 验证二叉树的前序序列化
 *
 */
public class T10
{
    public boolean isValidSerialization(String preOrder)
    {
        String[] str = preOrder.split(",");

        int dif = 1;

        for (String s : str)
        {
            if (--dif < 0)
                return false;
            if (!s.equals("#"))
                dif += 2;
        }

        return dif == 0;
    }

    public static void main(String[] args)
    {
        T10 t = new T10();
        Boolean b1 = t.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
        Boolean b2 = t.isValidSerialization("1,#,#");
        Boolean b3 = t.isValidSerialization("9,#,#,1");

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
    }
}
