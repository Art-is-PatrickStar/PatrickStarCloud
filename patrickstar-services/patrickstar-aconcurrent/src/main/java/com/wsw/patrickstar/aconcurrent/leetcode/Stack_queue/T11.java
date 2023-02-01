package com.wsw.patrickstar.aconcurrent.leetcode.Stack_queue;

import java.util.Stack;

/**
 * 括号的分数
 *
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 *
 */
public class T11 {
    public int scoreOfParentheses(String S) {
        Stack<String> stack = new Stack<>();

        int i = 0;
        while (i < S.length()){
            switch ("" + S.charAt(i)){
                case "(":
                    stack.push("(");
                    break;
                case ")":
                    String s = stack.pop();
                    if (s == "("){
                        stack.push(Integer.toString(1));
                    }
                    else {
                        int temp = Integer.parseInt(s);
                        while (true){
                            s = stack.pop();
                            if (s == "("){
                                stack.push(Integer.toString(temp * 2));
                                break;
                            }
                            else {
                                temp += Integer.parseInt(s);
                            }
                        }
                    }
                    break;
            }
            i++;
        }

        int res = 0;
        while (!stack.isEmpty()){
            res += Integer.parseInt(stack.pop());
        }
        return res;
    }

    public static void main(String[] args) {
        T11 t = new T11();

        int a = t.scoreOfParentheses("()");          //1
        int b = t.scoreOfParentheses("(())");        //2
        int c = t.scoreOfParentheses("(()(()))");    //6
        System.out.println(a + " " + b + " " + c + " ");
    }
}
