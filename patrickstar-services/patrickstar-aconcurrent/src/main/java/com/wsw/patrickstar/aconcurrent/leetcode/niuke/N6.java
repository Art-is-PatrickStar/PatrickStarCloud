package com.wsw.patrickstar.aconcurrent.leetcode.niuke;

/**
 * 数值的整数次方
 *
 */
public class N6 {
    public double Power(double base, int exponent) throws Exception{
        double result = 0.0;

        if (equal(base, 0.0) && exponent < 0)
            throw new Exception("0的负数次幂无意义！");

        if (equal(exponent, 0))
            return 1.0;

        if (exponent < 0)
            result = powerWithExponent(1.0 / base, -exponent);
        else
            result = powerWithExponent(base, exponent);

        return result;
    }

    //判断两个double型数据，计算机有误差
    private boolean equal(double num1, double num2){
        if (num1 - num2 > -0.0000001 && num1 - num2 < 0.0000001)
            return true;
        else
            return false;
    }

    //用右移代替了除以2，用与运算代替了求余运算符(%)
    private double powerWithExponent(double base, int exponent){
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;

        double result = powerWithExponent(base, exponent >> 1);
        result *= result;
        if ((exponent & 0x1) == 1)
            result *= base;

        return result;
    }

    public static void main(String[] args) throws Exception{
        N6 n6 = new N6();
        //System.out.println(n6.Power(0,-2));
        System.out.println(n6.Power(0,0));
        System.out.println(n6.Power(0,2));
        System.out.println(n6.Power(5,5));
        System.out.println(n6.Power(9,-2));
        System.out.println(n6.Power(-2,3));
        System.out.println(n6.Power(-2,-3));
    }
}
