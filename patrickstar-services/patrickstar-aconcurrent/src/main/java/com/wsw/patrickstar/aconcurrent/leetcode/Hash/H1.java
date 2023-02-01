package com.wsw.patrickstar.aconcurrent.leetcode.Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class H1
{
    public String[] findWords(String[] words)
    {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        List<String> list  = new ArrayList<>();

        String[] s = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};

        for (int i = 0; i < s.length; i++)
        {
            char[] ch = s[i].toCharArray();
            for (int j = 0; j < ch.length; j++)
            {
                hashMap.put(ch[j], i);
            }
        }

        for (int i = 0; i < words.length; i++)
        {
            String str = words[i].toLowerCase() ;
            char[] ch = str.toCharArray();

            int x = hashMap.get(ch[0]);

            boolean flag = true;

            for (int j = 1; j < ch.length; j++)
            {
                if (hashMap.get(ch[j]) != x)
                {
                    flag = false;
                    break;
                }
            }

            if (flag)
                list.add(words[i]);
        }

        words = new String[list.size()];

        int i = 0;
        for (String ss : list)
        {
            words[i] = ss;
            i++;
        }

        return words;
    }

    public static void main(String[] args)
    {
        H1 h = new H1();
        String[] s = h.findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"});

        for (int i = 0; i < s.length; i++)
        {
            System.out.print(s[i] + " ");
        }
    }
}
