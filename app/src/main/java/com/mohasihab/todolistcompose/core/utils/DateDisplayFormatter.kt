package com.mohasihab.todolistcompose.core.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateDisplayFormatter {
    fun SimpleDateFormat.formatterDateFull(): SimpleDateFormat =
        SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH)

    fun SimpleDateFormat.formatterDate(): SimpleDateFormat =
        SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

    fun SimpleDateFormat.formatterDisplayDate(): SimpleDateFormat =
        SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)

    fun SimpleDateFormat.formatterDisplayDateFull(): SimpleDateFormat =
        SimpleDateFormat("EEE, dd MMM yyyy, HH:mm", Locale.ENGLISH)

    fun SimpleDateFormat.formatterTime(): SimpleDateFormat =
        SimpleDateFormat("HH:mm", Locale.ENGLISH)

    fun SimpleDateFormat.formatterMonth(): SimpleDateFormat =
        SimpleDateFormat("MMM", Locale.ENGLISH)

    fun SimpleDateFormat.formatterDay(): SimpleDateFormat =
        SimpleDateFormat("EEEE", Locale.ENGLISH)

    fun SimpleDateFormat.formatterDayOfMonth(): SimpleDateFormat =
        SimpleDateFormat("dd", Locale.ENGLISH)

    fun Date.getDayName() : String{
        val vDateFormat: SimpleDateFormat = SimpleDateFormat().formatterDay()
        var vDateResult = ""
        vDateResult = vDateFormat.format(this).toString()
        return vDateResult
    }

    fun Date.getMonthName(): String {
        val vMonthFormat: SimpleDateFormat = SimpleDateFormat().formatterMonth()
        var vMonthResult = ""
        vMonthResult = vMonthFormat.format(this).toString()
        return vMonthResult
    }

    fun Date.getDayOfMonth(): String {
        val vDayOfMonth: SimpleDateFormat = SimpleDateFormat().formatterDayOfMonth()
        var vDayResult = ""
        vDayResult = vDayOfMonth.format(this).toString()
        return vDayResult
    }


    fun fromStringToDateLong(dateString: String): Long {
        var vDateLong: Long = 0
        try {
            val vDateFormat: SimpleDateFormat = SimpleDateFormat().formatterDateFull()
            var vDate: Date
            vDateFormat.parse(dateString).also { vDate = it }
            vDateLong = Converter.fromDate(vDate)!!
        } catch (ex: Exception) {
            vDateLong = 0
        }


        return vDateLong
    }

    fun fromStringToDateddMMMYYYY(dateString: String): String {
        var vDateResult: String = ""
        try {
            val vDateFormat: SimpleDateFormat = SimpleDateFormat().formatterDate()
            var vDate: Date
            vDateFormat.parse(dateString).also { vDate = it }
            vDateResult = stringDateDisplay(vDate)
        } catch (ex: Exception) {
            vDateResult = ""
        }


        return vDateResult
    }

    fun stringDate(date: Date): String {
        val vDateFormat: SimpleDateFormat = SimpleDateFormat().formatterDate()
        var vDateResult = ""
        vDateResult = vDateFormat.format(date).toString()
        return vDateResult
    }

    fun Date.toDefaultDisplay() : String {
        val vDateFormat: SimpleDateFormat = SimpleDateFormat().formatterDisplayDate()
        var vDateResult = ""
        vDateResult = vDateFormat.format(this).toString()
        return vDateResult
    }

    fun Long.toDate(): Date {
        return Date(this)
    }

    fun stringDateDisplay(date: Date): String {
        val vDateFormat: SimpleDateFormat = SimpleDateFormat().formatterDisplayDate()
        var vDateResult = ""
        vDateResult = vDateFormat.format(date).toString()
        return vDateResult
    }

    fun stringMonth(date: Date): String {
        val vMonthFormat: SimpleDateFormat = SimpleDateFormat().formatterMonth()
        var vMonthResult = ""
        vMonthResult = vMonthFormat.format(date).toString()
        return vMonthResult
    }

    fun stringTime(date: Date): String {
        val vTimeFormat: SimpleDateFormat = SimpleDateFormat().formatterTime()
        var vTimeResult = ""
        vTimeResult = vTimeFormat.format(date).toString()
        return vTimeResult
    }

    fun stringDateWithHourMinute(date: Date): String {
        val vDateFormat: SimpleDateFormat = SimpleDateFormat().formatterDisplayDateFull()
        var vDateResult = ""
        vDateResult = vDateFormat.format(date).toString()
        return vDateResult


    }

    fun String.convertISOTimeToDate(pattern: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val convertedDate: Date?
        var formattedDate = "-"
        try {
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            convertedDate = sdf.parse(this)
            formattedDate =
                SimpleDateFormat(pattern, Locale("id", "ID")).format(convertedDate!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return formattedDate
    }


}