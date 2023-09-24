package com.example.movieapp.presentation.common

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


object DateUtils {
    fun stringToDate(dateStr: String, inputFormat: String): Date {
        val formatter = SimpleDateFormat(inputFormat)
        return formatter.parse(dateStr)
    }

    fun Date.getStrYear(): String {
        val calendar = Calendar.getInstance()
        calendar.time = this
        val year = calendar.get(Calendar.YEAR)
        return year.toString()
    }

    fun getYearFromStringDate(strDate: String, format: String = "yyyy-MM-dd"): String {
        return try {
            val date = stringToDate(strDate, format)
            val year = date.getStrYear()
            year
        } catch (e: Exception) {
            ""
        }
    }

}