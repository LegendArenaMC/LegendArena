/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.api.utils

import java.text.SimpleDateFormat
import java.util.*

class CalendarUtils {

    private val cal = Calendar.getInstance()
    private var day = 0
    private var month = 0
    private var dayOfYear = 0

    private var year = 2016

    constructor() {
        month = cal.get(Calendar.MONTH) + 1 //damnit Calendar, why does your months have to be zero-indexed...
        day = cal.get(Calendar.DAY_OF_MONTH)
        year = cal.get(Calendar.YEAR)
        dayOfYear = cal.get(Calendar.DAY_OF_YEAR)
    }

    fun getDay(): Int {
        return day
    }

    fun getMonth(): Int {
        return month
    }

    fun getYear(): Int {
        return year
    }

    /**
     * Use getDateFormat(DateLocale().US) to override the default of UK formatting.
     */
    fun getDateString(): String {
        return getDateString(DateLocale.UK)
    }

    fun getDateString(locale: DateLocale): String {
        return (if (locale === DateLocale.UK) "" + day + "/" + month else "" + month + "/" + day) + "/" + year
    }

    fun getDayString(fullString: Boolean): String {
        return getDayString(fullString, DateLocale.UK)
    }

    fun getDayString(fullString: Boolean, locale: DateLocale): String {
        return SimpleDateFormat(if(fullString) "EEEE" else "E", if(locale == DateLocale.UK) Locale.UK else Locale.US).format(cal.time)
    }

    fun isMonth(m: Months): Boolean {
        return getMonth() == m.monthId
    }

    enum class DateLocale {
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

    fun parseMonth(): String {
        return when(month) {
            1 -> "January"
            2 -> "Feburary"
            3 -> "March"
            4 -> "April"
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "August"
            9 -> "September"
            10 -> "October"
            11 -> "November"
            12 -> "December"

            else -> "Error :("
        }
    }

    fun parseMonth(month: Months): String {
        return when(month.monthId) {
            1 -> "January"
            2 -> "Feburary"
            3 -> "March"
            4 -> "April"
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "August"
            9 -> "September"
            10 -> "October"
            11 -> "November"
            12 -> "December"

            else -> "Unknown month number " + month
        }
    }

}