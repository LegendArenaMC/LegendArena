package net.thenamedev.legendapi.utils;

import java.util.Calendar;

/**
 * Created on 4/5/2015
 *
 * @author ThePixelDev
 */
public class Day {

    private int day;
    private int month;

    private Day(int day, int month) {
        this.day = day;
        this.month = month;
    }

    public boolean isAprilFools() {
        return getDay() == 1 && getMonth() == APRIL;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public static Day getDate() {
        return new Day(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.MONTH) + 1);
    }

    public static final int JANUARY = 1;
    public static final int FEBURARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;

}
