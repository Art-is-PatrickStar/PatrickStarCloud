package com.wsw.patrickstar.aconcurrent.leetcode.Map;

import java.util.Comparator;

//自定义App类的比较器：
public class PersonComparator implements Comparator<Person> {

    //比较方法：先比较年龄，年龄若相同在比较名字长度；
    public int compare(Person person1, Person person2) {
        int num = person1.getAge() - person2.getAge();
        return num == 0 ? person1.getName().length() - person2.getName().length() : num;
    }
}