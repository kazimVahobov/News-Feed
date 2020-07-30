package com.example.newsfeed.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {

        fun formattingDate(sourceDate: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val outputFormat = SimpleDateFormat("dd.MM.yyyy")
            val date: Date = inputFormat.parse(sourceDate)
            return outputFormat.format(date)
        }
    }
}

fun View.snackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snack ->
        snack.setAction("Ok") {
            snack.dismiss()
        }
    }.show()
}