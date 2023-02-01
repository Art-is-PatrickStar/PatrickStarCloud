package com.wsw.patrickstar.aconcurrent.leetcode.Sort;

import java.util.Arrays;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
 *
 */
public class S3
{
    public boolean isAnagram(String s, String t)
    {
        if (s.length() != t.length())
            return false;

        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();

        Arrays.sort(chars1);
        Arrays.sort(chars2);

        for (int i = 0; i < chars1.length; i++)
        {
            if (chars1[i] != chars2[i])
                return false;
        }

        return true;
    }
    /*
    public boolean isAnagram(String s, String t)
    {
        char[] chars = new char[26];

        for (char c : s.toCharArray())
        {
            chars[c - 'a']++;
        }

        for (char c : t.toCharArray())
        {
            chars[c - 'a']--;
        }

        for (int i = 0; i < chars.length; i++)
        {
            if (chars[i] != 0)
                return false;
        }

        return true;
    }*/

    public static void main(String[] args)
    {
        S3 s = new S3();
        System.out.println(s.isAnagram("anagram", "nagaram"));
        System.out.println(s.isAnagram("rat", "cat"));
    }
}
