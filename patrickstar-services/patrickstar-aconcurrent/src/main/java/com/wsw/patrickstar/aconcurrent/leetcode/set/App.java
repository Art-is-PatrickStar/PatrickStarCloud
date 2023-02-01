package com.wsw.patrickstar.aconcurrent.leetcode.set;

public class App{

    private String name;

    private Integer age;

    public App(){}

    public App(String name,Integer age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{name = " + name + ',' + "age = " + age + "}";
    }
}