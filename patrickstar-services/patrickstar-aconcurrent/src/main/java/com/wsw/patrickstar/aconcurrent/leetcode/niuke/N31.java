package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by wsw on 2019/9/14 16:07
 * 把数组排成最小的数
 */
public class N31 {
    public String PrintMinNumber(int [] numbers) {
        if (numbers == null || numbers.length == 0)
            return "";

        int length = numbers.length;
        StringBuilder s = new StringBuilder();
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            arrayList.add(numbers[i]);
        }

        //比较器就是一个接口，通过实现这个接口重写compare方法，返回正值代表大于，返回0代表等于，返回负值代表小于。
        //关于比较器，比如例题中的{3，32，321} 数组中先放入3，而后3和32比较，因为332>323 所以3>32 数组此时为[32,3];
        //再往数组中加入321，先与32比较，32132<32321 故 321<32  故321应排在32前面，再与3比较 3213<3321 故321<3 数组最终排序[321，32，3]
        arrayList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = o1 + "" + o2;
                String s2 = o2 + "" + o1;
                return s1.compareTo(s2);
            }
        });

        for (Integer integer : arrayList) {
            s.append(integer);
        }

        return s.toString();
    }

    public static void main(String[] args) {
        N31 n31 = new N31();
        int[] nums = {3, 32, 321};
        String res = n31.PrintMinNumber(nums);
        System.out.println(res);
    }
}
