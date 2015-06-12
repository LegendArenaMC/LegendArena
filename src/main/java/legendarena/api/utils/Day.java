package legendarena.api.utils;

import java.util.Calendar;

/**
 * Calendar utils.
 *
 * @author ThePixelDev
 */
public class Day {

    private int day;
    private int month;
    private int dayOfYear;
    private int year;

    private Day(int day, int month, int year, int dayOfYear) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.dayOfYear = dayOfYear;
    }

    public String getDateString() {
        return getDay() + "/" + getMonth() + "/" + getYear();
    }

    public String getDateFormat(DateLocale locale) {
        return (locale == DateLocale.UK ? getDay() + "/" + getMonth() : getMonth() + "/" + getDay()) + "/" + getYear();
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getDayOfYear() {
        return dayOfYear;
    }

    public static Day getDate() {
        return new Day(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.MONTH) + 1, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
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

    public static String parseMonth() {
        switch(getDate().getMonth()) {
            case 1:
                return "January";
            case 2:
                return "Feburary";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";

            default:
                return "Error :(";
        }
    }

    public static String parseMonth(int month) {
        switch(month) {
            case 1:
                return "January";
            case 2:
                return "Feburary";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";

            default:
                return "Unknown month ID " + month;
        }
    }

    public enum DateLocale {
        US,
        UK
    }

}
