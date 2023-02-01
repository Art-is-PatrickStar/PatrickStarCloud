package com.wsw.patrickstar.aconcurrent.leetcode.Stack_queue;

import java.util.Stack;

/**
 * 比较含退格的字符串
 *
 */
public class T8
{
    /**
     *给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
     *
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare(String S, String T)
    {
        return compute(S).equals(compute(T));
    }

    private Stack<Character> compute(String s)
    {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray())
        {
            if (c != '#')
                stack.push(c);
            else
            {
                if (!stack.isEmpty())
                    stack.pop();
            }
        }

        return stack;
    }

/*    public boolean backspaceCompare(String S, String T) {
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();

        for (Character c : S.toCharArray()){
            if (c != '#'){
                stack1.push(c);
            }
            else {
                if (!stack1.isEmpty()){
                    stack1.pop();
                }
            }
        }

        for (Character c : T.toCharArray()){
            if (c != '#'){
                stack2.push(c);
            }
            else {
                if (!stack2.isEmpty()){
                    stack2.pop();
                }
            }
        }

        return stack1.equals(stack2);
    }*/


    public static void main(String[] args)
    {
        T8 t = new T8();

        String S1 = "a##b";
        String T1 = "#a#b";
        System.out.println(t.backspaceCompare(S1, T1));

        String S2 = "a#c";
        String T2 = "b";
        System.out.println(t.backspaceCompare(S2, T2));

    }
}
