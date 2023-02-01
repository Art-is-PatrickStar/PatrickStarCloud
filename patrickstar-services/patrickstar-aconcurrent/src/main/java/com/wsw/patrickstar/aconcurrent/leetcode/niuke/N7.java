package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 * 正则表达式匹配
 *
 */
public class N7 {
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null)
            return false;

        if (str.length == 0 && pattern.length == 0)
            return true;

        return matchCore(str, 0, pattern, 0);
    }

    private static boolean matchCore(char[] str, int p1, char[] pattern, int p2){
        //模式串用完
        if (p2 >= pattern.length)
            return p1 >= str.length;

        //字符串匹配完
        if (p1 >= str.length){
            if (p2 + 1 < pattern.length && pattern[p2 + 1] == '*')
                return matchCore(str, p1, pattern, p2 + 2);
            else
                return false;
        }

        //如果p2的后面是'*'
        if (p2 + 1 < pattern.length && pattern[p2 + 1] == '*'){
            if (pattern[p2] == '.' || pattern[p2] == str[p1])
                return matchCore(str, p1+1, pattern, p2) || matchCore(str, p1, pattern, p2+2);
            else
                return matchCore(str, p1, pattern, p2 + 2);
        }

        else {
            if (pattern[p2] == '.' || pattern[p2] == str[p1])
                return matchCore(str,p1+1, pattern,p2+1);
            else
                return false;
        }
    }

    public static void main(String[] args) {
        N7 n7 = new N7();
        System.out.println(n7.match(new char[]{'a', 'a', 'a'}, new char[]{'a','a','*','.','c','*','a'}));
        System.out.println(n7.match(new char[]{'a', 'a', 'a'}, new char[]{'a','b','*','a'}));
    }
}
