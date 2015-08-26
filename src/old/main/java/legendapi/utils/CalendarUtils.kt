/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.utils

import java.text.SimpleDateFormat
import java.util.*

public class CalendarUtils {

    private val cal = Calendar.getInstance()
    private var day = 0
    private var month = 0
    private var dayOfYear = 0

    //for people who are currently yelling at their screens "DON'T HARDCODE THE YEAR YOU IDIOT":
    //this is hardcoded /default/ - it's always overriden on instance creation - see constructor function
    private var year = 2015

    public constructor() {
        month = cal.get(Calendar.MONTH) + 1 //damnit Calendar, why does your months have to be zero-indexed...
        day = cal.get(Calendar.DAY_OF_MONTH)
        year = cal.get(Calendar.YEAR)
        dayOfYear = cal.get(Calendar.DAY_OF_YEAR)
    }

    public fun getDay(): Int {
        return day
    }

    public fun getMonth(): Int {
        return month
    }

    public fun getYear(): Int {
        return year
    }

    /**
     * Use getDateFormat(DateLocale().US) to override the default of UK formatting.
     */
    public fun getDateString(): String {
        return getDateString(DateLocale.UK)
    }

    public fun getDateString(locale: DateLocale): String {
        return (if (locale === DateLocale.UK) "" + day + "/" + month else "" + month + "/" + day) + "/" + year
    }

    public fun getDayString(fullString: Boolean): String {
        return getDayString(fullString, DateLocale.UK)
    }

    public fun getDayString(fullString: Boolean, locale: DateLocale): String {
        return SimpleDateFormat(if(fullString) "EEEE" else "E", if(locale == DateLocale.UK) Locale.UK else Locale.US).format(cal.getTime())
    }

    public fun isMonth(m: Months): Boolean {
        return getMonth() == m.getMonthId()
    }

    public enum class DateLocale {
        /**
         * US date formatting:<br>
         *
         * <strong>MONTH/DAY/YEAR</strong>
         */
        US,
        /**
         * UK date formatting:<br>
         *
         * <strong>DAY/MONTH/YEAR</strong>
         */
        UK

    }

    public fun parseMonth(): String {
        when(month) {
            1 -> return "January"
            2 -> return "Feburary"
            3 -> return "March"
            4 -> return "April"
            5 -> return "May"
            6 -> return "June"
            7 -> return "July"
            8 -> return "August"
            9 -> return "September"
            10 -> return "October"
            11 -> return "November"
            12 -> return "December"

            else -> return "Error :("
        }
    }

    public fun parseMonth(month: Months): String {
        when(month.getMonthId()) {
            1 -> return "January"
            2 -> return "Feburary"
            3 -> return "March"
            4 -> return "April"
            5 -> return "May"
            6 -> return "June"
            7 -> return "July"
            8 -> return "August"
            9 -> return "September"
            10 -> return "October"
            11 -> return "November"
            12 -> return "December"

            else -> return "Unknown month number " + month
        }
    }

}