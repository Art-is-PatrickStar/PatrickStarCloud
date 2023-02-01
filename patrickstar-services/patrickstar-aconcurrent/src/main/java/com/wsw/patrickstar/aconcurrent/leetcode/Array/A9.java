package com.wsw.patrickstar.aconcurrent.leetcode.Array;

/**
 * 替换空格
 *
 */
public class A9 {
    public String replaceSpace(String str) {
        String s = str.toString();

        if (s.equals(""))
            return s;

        char[] array = s.toCharArray();
        int i = 0;
        int lengthOfSpace = 0;
        while (i < array.length){
            if (array[i] == ' ')
                lengthOfSpace++;
            i++;
        }

        int newLength = array.length + 2 * lengthOfSpace;
        char[] newArray = new char[newLength];
        int j = newLength - 1;
        i = array.length - 1;
        while (i >= 0){
            if (array[i] != ' '){
                newArray[j--] = array[i--];
            }
            else {
                newArray[j--] = '0';
                newArray[j--] = '2';
                newArray[j--] = '%';
                i--;
            }
        }

        return new String(newArray);
    }

    public static void main(String[] args) {
        A9 a = new A9();
        System.out.println(a.replaceSpace("we are happy."));
    }
}
