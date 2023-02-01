package com.wsw.patrickstar.aconcurrent.leetcode.Array;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 合并数组区间
 */
public class A2
{
    public static class Interval
    {
      int start;
      int end;
      Interval() {
          start = 0;
          end = 0;
      }
      Interval(int s, int e) {
          start = s;
          end = e;
      }
    }

    public List<Interval> merge(List<Interval> intervals)
    {
        Collections.sort(intervals, new Comparator<Interval>()
        {
            @Override
            public int compare(Interval o1, Interval o2)
            {
                if (o1.start > o2.start)
                    return 1;
                else if (o1.start < o2.start)
                    return -1;
                else
                    return 0;
            }
        });

        int i = 0;
        while (i < intervals.size())
        {
            if (i != intervals.size() - 1)
            {
                if (intervals.get(i).end >= intervals.get(i + 1).start && intervals.get(i).end <= intervals.get(i + 1).end)
                {
                    int tempStart = intervals.get(i).start;
                    int tempEnd = intervals.get(i + 1).end;
                    intervals.remove(i);
                    intervals.get(i).start = tempStart;
                    intervals.get(i).end = tempEnd;
                }
                else if (intervals.get(i).end < intervals.get(i + 1).start)
                {
                    i++;
                }
                else if (intervals.get(i).end > intervals.get(i + 1).end)
                {
                    intervals.remove(i + 1);
                }
            }
            else
            {
                i++;
            }
        }

        return intervals;
    }

    public static void main(String[] args)
    {
        A2 a = new A2();
        Interval interval1 = new Interval(2,3);
        Interval interval2 = new Interval(4,5);
        Interval interval3 = new Interval(6,7);
        Interval interval4 = new Interval(8,9);
        Interval interval5 = new Interval(1,10);
        List<Interval> list = new LinkedList<>();
        list.add(interval1);
        list.add(interval2);
        list.add(interval3);
        list.add(interval4);
        list.add(interval5);
        List<Interval> res = a.merge(list);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i).start + "," + res.get(i).end);
        }
    }
}
