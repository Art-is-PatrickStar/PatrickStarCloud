package com.wsw.patrickstar.aconcurrent.lamda.filter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/8/24 15:16
 * Copyright © 2022 Hundsun Technologies Inc. All Rights Reserved
 */
public class Test {
    public static class Resource {
        private Integer id;
        private String name;
        private String md5;
        private Date date;

        @Override
        public String toString() {
            return id + ", " + name + ", " + md5 + ", " + date + " --- ";
        }

        public Resource(Integer id, String name, String md5, Date date) {
            this.id = id;
            this.name = name;
            this.md5 = md5;
            this.date = date;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }

    public static void main(String[] args) {
        List<Resource> list = new ArrayList<>();
        Resource resource1 = new Resource(3, "c", "abc", new Date());
        Resource resource2 = new Resource(1, "a", "abc", new Date());
        Resource resource3 = new Resource(2, "b", "abc", new Date());
        Resource resource4 = new Resource(5, "b", "cab", new Date());
        Resource resource5 = new Resource(6, "b", "bab", new Date());
        Resource resource6 = new Resource(4, "b", "abca", new Date());
        list.add(resource1);
        list.add(resource2);
        list.add(resource3);
        list.add(resource4);
        list.add(resource5);
        list.add(resource6);
        System.out.println("first: " + list);
        Collections.reverse(list);
        list = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Resource::getMd5))), ArrayList::new));
        System.out.println("second: " + list);
    }
}
