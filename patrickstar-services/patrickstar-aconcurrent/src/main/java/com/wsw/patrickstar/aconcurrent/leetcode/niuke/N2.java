package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 * 剑指offer——替换空格
 *
 */
public class N2 {
    public String replaceSpace(StringBuffer str) {
        String string = str.toString();

        if (string.equals(""))
            return string;

        char[] strArray = string.toCharArray();
        int i = 0;
        int lengthTemp = 0;

        while (i < strArray.length){
            //System.out.println(strArray[i]);
            if (strArray[i] == ' ')
                lengthTemp++;
            i++;
        }

        int newLength = strArray.length + lengthTemp * 2;
        char[] newStrArray = new char[newLength];
        int j = newStrArray.length - 1;
        i = strArray.length - 1;

        while (i >= 0){
            if (strArray[i] != ' ')
                newStrArray[j--] = strArray[i--];
            else{
                newStrArray[j--] = '0';
                newStrArray[j--] = '2';
                newStrArray[j--] = '%';
                i--;
            }
        }

        return new String(newStrArray);
    }

    public static void main(String[] args) {
        N2 n = new N2();
        String s = "We Are Happy!";
        StringBuffer buffer = new StringBuffer(s);
        String res = n.replaceSpace(buffer);
        System.out.println(res);
    }
}
