package com.wsw.patrickstar.aconcurrent.leetcode.qiuzhao.zhangshang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsw on 2019/9/25 18:50
 * 实现split()分隔方法
 */
public class SplitString {
    public static String[] split(String array, String target) {
        List<String> list = new ArrayList<>();
        while (array.contains(target)) {
            int index = array.indexOf(target);
            String temp = array.substring(0, index);
            list.add(temp);
            array = array.substring(index + target.length()); //substring(endIndex)删除endIndex之前的字符串
        }
        list.add(array);
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static List<String> split1(String array, String target){
        List<String> result = new ArrayList<>();
        char[] arrayChar = array.toCharArray();
        char[] targetChar = target.toCharArray();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < arrayChar.length;){
            if(!isContains(arrayChar,targetChar,i)){
                sb.append(arrayChar[i]);
                i++;
            }else {
                result.add(sb.toString());
                sb = new StringBuffer();
                i += targetChar.length;
            }
        }
        if(sb.length() != 0){
            result.add(sb.toString());
        }
        return result;
    }

    public static boolean isContains(char[] srcArray, char[] targetArray, int tmp){
        for(int i = 0; i<targetArray.length; i++){
            if(tmp + i >= srcArray.length)
                return false;
            if(srcArray[i + tmp] != targetArray[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String array = "312456781124789";
        String target = "12";
        //String[] resList = split(array, target);
        List<String> resList = split1(array, target);
        for (String s : resList) {
            System.out.print(s + " ");
        }
    }
}
