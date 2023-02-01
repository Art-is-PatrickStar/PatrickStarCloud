package com.wsw.patrickstar.aconcurrent.steam;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: wangsongwen
 * @Date: 2021/9/30 9:35
 * @Description: TODO
 */
public class Test {
    public static void main(String[] args) {
        /*Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);

        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        Stream.of("1", 2, 3, 4)
                .limit(2)
                .skip(1)
                .forEach(System.out::println);

        List<String> list = new ArrayList<>();
        list.add("王松文");
        list.add("王松");
        list.add("文");
        list.stream()
                .filter(name -> name.startsWith("王"))
                .filter(name -> name.length() == 3)
                .forEach(System.out::println);*/
//        String s = UUID.randomUUID().toString().replaceAll("-", "");
//        Long id = new Long(s);
//        System.out.println(id);


//        Set<String> noMobileUserSet = new HashSet<>();
//        Set<String> nullUserInfoSet = new HashSet<>();
//        List<String> noMobileUserList = new ArrayList<>();
//        List<String> nullUserInfoList = new ArrayList<>();
//        List<String> result = null;
//
//        noMobileUserList.add("151");
//        noMobileUserList.add("123");
//        noMobileUserList.add("176");
//
//        noMobileUserSet.add("151");
//        noMobileUserSet.addAll(noMobileUserList);
//
//        nullUserInfoList.add("wangsongwen");
//        nullUserInfoList.add("yanglh");
//
//        nullUserInfoSet.add("yanglh");
//        nullUserInfoSet.addAll(nullUserInfoList);
//
//        result = new ArrayList<>(nullUserInfoSet);
//        System.out.println(result);

        long time = 1645781424751L;
        Date date = new Date(time);
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        String s = sdf.format(date);
        System.out.println(s);
    }
}
