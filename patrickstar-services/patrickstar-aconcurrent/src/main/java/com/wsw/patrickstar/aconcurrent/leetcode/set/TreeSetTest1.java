package com.wsw.patrickstar.aconcurrent.leetcode.set;

import java.util.TreeSet;

public class TreeSetTest1 {
    public static void main(String[] agrs){
        customSort();
    }

    //自定义排序顺序：升序
    public static void customSort(){
        TreeSet<App> treeSet = new TreeSet<>(new AppComparator());

        //排序对象：
        App app1 = new App("hello",10);
        App app2 = new App("world",20);
        App app3 = new App("my",15);
        App app4 = new App("name",25);

        //添加到集合：
        treeSet.add(app1);
        treeSet.add(app2);
        treeSet.add(app3);
        treeSet.add(app4);
        System.out.println("TreeSet集合顺序为："+treeSet);
    }
}
