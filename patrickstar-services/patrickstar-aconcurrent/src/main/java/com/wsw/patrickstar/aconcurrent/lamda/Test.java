package com.wsw.patrickstar.aconcurrent.lamda;

import java.util.Arrays;

/**
 * @Author: wangsongwen
 * @Date: 2021/9/30 10:37
 * @Description: TODO
 */
public class Test {
    public static void invokeCook(Cook cook) {
        cook.sayHello();
    }

    public static void invokeAdd(int a, int b, Add add) {
        int sum = add.add(a, b);
        System.out.println(sum);
    }

    public static void main(String[] args) {
        /*invokeCook(new Cook() {
            @Override
            public void sayHello() {

            }
        });*/

        invokeCook(() -> {
            System.out.println("hello");
        });

        Person[] people = {new Person("wsw", 3), new Person("www", 2), new Person("sss", 1)};

        /*Arrays.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return 0;
            }
        });*/

        Arrays.sort(people, (p1, p2) -> {
            return p1.getAge() - p2.getAge();
        });

        //Arrays.sort(people, Comparator.comparingInt(Person::getAge));

        for (Person person : people) {
            System.out.println(person);
        }

        invokeAdd(1, 2, (int a, int b) -> {
            return a + b;
        });
    }
}
