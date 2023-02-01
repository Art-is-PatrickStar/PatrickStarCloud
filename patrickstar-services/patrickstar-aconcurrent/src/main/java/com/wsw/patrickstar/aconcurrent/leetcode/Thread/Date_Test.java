package com.wsw.patrickstar.aconcurrent.leetcode.Thread;

import java.util.Date;

public class Date_Test
{
    public static Date getNextDay(Date date)
    {
        int addTime = 1 * 24 * 60 * 60 * 1000;
        Date nextDay = new Date(date.getTime() + addTime);
        return nextDay;
    }

    public static void main(String[] args)
    {
        Date now = new Date();
        System.out.println("Now: " + now);
        System.out.println("NextDay: " + getNextDay(now));
    }
}
