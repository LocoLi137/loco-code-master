package com.loco.normal;

import java.time.LocalDate;

/**
 * description: 日期计算
 */
public class DateCalculate {
    /**
     * 计算两个日期相差天数
     *      先根据年月日求出该日期距离 0001年1月1日 的总天数，
     *      两个天数直接相减，即可求出日期差。
     */
    public int numberOfTwoDaysDifferent(LocalDate start, LocalDate end){
        int startDate = DateUtils.countDays(start.getYear(), start.getMonthValue(), start.getDayOfMonth());
        int endDate = DateUtils.countDays(end.getYear(), end.getMonthValue(), end.getDayOfMonth());
        return endDate - startDate;
    }


    class DateUtils{
        /**
         * 根据年月日求出该日期距离 0001年1月1日 的总天数
         */
        private static int countDays(int y, int m, int d){
            if (m < 3){
                y --;
                m += 12;
            }
            return 365 * y + (y >> 2) - y / 100 + y / 400 + (153 * m - 457) / 5 + d - 306;
        }
    }


}
