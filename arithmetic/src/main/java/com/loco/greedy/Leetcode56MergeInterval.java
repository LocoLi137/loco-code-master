package com.loco.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Leetcode56MergeInterval {
    public static void main(String[] args) {
        Leetcode56MergeInterval interval = new Leetcode56MergeInterval();
        int[][] nums = {{1,3}, {2,6}, {8,10}, {15,18}};
        int[][] merge = interval.merge(nums);
        for (int[] ints : merge) {
            for (int anInt : ints) {
                System.out.print(anInt+ " ");
            }
            System.out.println();
        }
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return intervals;
        }

        //按每行的第0列升序排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> integer = new ArrayList<>();
        //定义一个Int类型数组用于作比较，默认值为第一组二维数组的值。
        int[] ints = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            //如果第一个数组的右端点大于等于下一个数组的左端点，做说明两个数组有所交集。
            if (ints[1] >= intervals[i][0]) {
                //int类型数组的右端点等于两个数组中右端点大的那个值。
                ints[1] = Math.max(ints[1], intervals[i][1]);
            } else {
                //把int类型一维数组ints添加到我们创建的list类里面。
                integer.add(ints);
                //给一维数组重新赋值。
                ints = intervals[i];
            }
        }

        integer.add(ints);
        return integer.toArray(new int[integer.size()][2]);
    }
}
