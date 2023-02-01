package com.wsw.patrickstar.aconcurrent.leetcode.qiuzhao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * JAVA基础
 */
public class Q1 {
    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    /**
     * ==与equals的区别
     */
    private static void test1(){
        String a = new String("ab"); // a 为一个引用
        String b = new String("ab"); // b为另一个引用,对象的内容一样
        String aa = "ab"; // 放在常量池中
        String bb = "ab"; // 从常量池中查找
        if (aa == bb) // true
            System.out.println("aa==bb");
        if (a == b) // false，非同一对象
            System.out.println("a==b");
        if (a.equals(b)) // true
            System.out.println("aEQb");
        if (42 == 42.0) { // true
            System.out.println("true");
        }
    }

    /**
     * 自动装箱和拆箱
     */
    private static void test2(){
        Integer i1 = 10; // 自动装箱
        Integer i2 = new Integer(10); // 非自动装箱
        Integer i3 = Integer.valueOf(10); // 非自动装箱
        int i4 = new Integer(10); // 自动拆箱
        int i5 = i2.intValue(); // 非自动拆箱
        System.out.println("i1 = [" + i1 + "]");
        System.out.println("i2 = [" + i2 + "]");
        System.out.println("i3 = [" + i3 + "]");
        System.out.println("i4 = [" + i4 + "]");
        System.out.println("i5 = [" + i5 + "]");
        System.out.println("i1 == i2 is [" + (i1 == i2) + "]");
        System.out.println("i1 == i4 is [" + (i1 == i4) + "]"); // 自动拆箱
    }

    /**
     * 重写equals方法必须同时重写hashCode方法
     */
    private static void test3(){
        User user1=new User();
        user1.setName("路西");
        user1.setAge(18);
        user1.setIdCard("430");
        User user2=new User();
        user2.setName("路西");
        user2.setAge(18);
        user2.setIdCard("430");
        System.out.println("user1.equals(user2)="+user1.equals(user2));
    }


}

@Getter
@Setter
@ToString
class User{
    private String name;
    private String idCard;
    private int age;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;
            if (user.getName().equals(this.name) && user.getIdCard().equals(this.idCard) && user.getAge() == this.age){
                return true;
            }else
                return false;
        }else {
            return false;
        }
    }
}
