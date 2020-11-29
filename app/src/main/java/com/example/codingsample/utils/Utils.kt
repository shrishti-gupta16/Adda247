package com.example.codingsample.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {

        fun formatDate(time: String): String {
            val originalFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val targetFormat: DateFormat = SimpleDateFormat("dd MMM yyyy")
            val date: Date = originalFormat.parse(time)
            return targetFormat.format(date)
        }
    }
}
