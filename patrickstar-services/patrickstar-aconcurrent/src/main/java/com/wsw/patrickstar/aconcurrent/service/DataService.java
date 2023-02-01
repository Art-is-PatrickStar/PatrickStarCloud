package com.wsw.patrickstar.aconcurrent.service;

/**
 * @Author WangSongWen
 * @Date: Created in 9:14 2021/3/11
 * @Description:
 */
public class DataService {
    public static boolean dataIfRepeat(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dataIfNotSuccession(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1 && array[i] + 1 != array[i + 1]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 7};
        boolean a = dataIfRepeat(array);
        boolean b = dataIfNotSuccession(array);
        System.out.println(a + " " + b);
    }
}
