package com.wsw.patrickstar.aconcurrent.leetcode.fanshe;

import java.lang.reflect.Method;

/**
 * Created by wsw on 2019/9/24 14:08
 * 通过反射调用方法
 */
public class methodClass {
    public static void main(String[] args) throws Exception {
        //Class<?> mClass = MethodUse.class;
        Class<?> mClass = Class.forName("com.wsw.leetcode.fanshe.MethodUse");
        //创建methodClass的实例
        Object mObject = mClass.getDeclaredConstructor().newInstance();
        //获取methodClass类的add方法
        Method method = mClass.getMethod("sub", int.class, int.class);
        //调用method对应的方法 => add(1,4)
        Object result = method.invoke(mObject, 1, 4);
        System.out.println(result);
    }
}

class MethodUse{
    public final int n = 3;
    public int add(int a, int b){
        return a + b;
    }
    public int sub(int a, int b){
        return a - b;
    }
}