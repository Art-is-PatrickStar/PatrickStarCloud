package com.wsw.patrickstar.aconcurrent.leetcode.fanshe;

/**
 * Created by wsw on 2019/9/24 11:55
 */
public class SonClass extends FatherClass {
    private String sonName;
    protected int sonAge;
    public String sonBirthday;

    public void printSonMag(){
        System.out.println("Son Msg - name : " + sonName + ", age : " + sonAge);
    }

    public String getSonName() {
        return sonName;
    }

    public void setSonName(String sonName) {
        this.sonName = sonName;
    }

    public int getSonAge() {
        return sonAge;
    }

    public void setSonAge(int sonAge) {
        this.sonAge = sonAge;
    }
}
