package com.wsw.patrickstar.aconcurrent.leetcode.Stack_queue;

import java.util.LinkedList;

/**
 * 简化路径
 *
 */
public class T10 {
    public String simplifyPath(String path) {
        LinkedList<String> queue = new LinkedList<>();

        String[] paths = path.split("/");
        for (String s : paths){
            if (s.equals(".") || s.equals(""))
                continue;
            else if (s.equals("..")){
                if (!queue.isEmpty())
                    queue.removeLast();
            }
            else{
                queue.addLast("/" + s);
            }
        }

        if (queue.isEmpty())
            return "/";
        else{
            StringBuilder newPath = new StringBuilder();
            while (!queue.isEmpty()){
                newPath.append(queue.removeFirst());
            }
            return newPath.toString();
        }
    }

    public static void main(String[] args) {
        T10 t = new T10();
        System.out.println(t.simplifyPath("/a/./b/../../c/"));
        System.out.println(t.simplifyPath("/a/../../b/../c//.//"));
        System.out.println(t.simplifyPath("/a//b////c/d//././/.."));
    }
}
