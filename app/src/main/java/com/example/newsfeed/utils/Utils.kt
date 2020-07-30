package com.example.newsfeed.utils

import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {

        fun formattingDate(sourceDate: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val outputFormat = SimpleDateFormat("dd.MM.yyyy")
            val date: Date = inputFormat.parse(sourceDate)
            return outputFormat.format(date)
        }
    }
}