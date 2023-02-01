package com.wsw.patrickstar.aconcurrent.leetcode.Stack_queue;

import java.util.Stack;

/**
 *逆波兰式
 *
 */
public class T6
{
    /**
     * 遇到数字就入栈，遇到运算符后就让两个操作数出栈完成运算后再入栈
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens)
    {
        Stack<Integer> stack = new Stack<>();

        if (tokens == null || tokens.length == 0)
            return 0;

        for (int i = 0; i < tokens.length; i++)
        {
            if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/"))
            {
                if (tokens[i].equals("+"))
                {
                    int num1 = stack.pop();
                    int num2 = stack.pop();
                    stack.push(num1 + num2);
                }

                if (tokens[i].equals("-"))
                {
                    int num1 = stack.pop();
                    int num2 = stack.pop();
                    stack.push(num2 - num1); //顺序
                }

                if (tokens[i].equals("*"))
                {
                    int num1 = stack.pop();
                    int num2 = stack.pop();
                    stack.push(num1 * num2);
                }

                if (tokens[i].equals("/"))
                {
                    int num1 = stack.pop();
                    int num2 = stack.pop();
                    stack.push(num2 / num1);
                }
            }

            else
                stack.push(Integer.parseInt(tokens[i]));
        }

        return stack.peek();
    }

    public static void main(String[] args)
    {
        T6 t = new T6();

        int end1 = t.evalRPN(new String[]{"2", "1", "+", "3", "*"});
        System.out.println(end1);

        int end2 = t.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"});
        System.out.println(end2);
    }
}
