package com.e.accessiblenews

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.format.DateTimeFormatter

/* This class is currently not used but I may implement it down the road */
class Utils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(value: String): String {
        var dateFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val parsedDate = dateFormatter.parse(value)
        dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy")
        val formattedDate = dateFormatter.format(parsedDate)
        return formattedDate
    }
}

