package com.sina.cinamovie.util

import timber.log.Timber
import java.lang.Exception
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

fun String.getAge(): Int {
    return try {
        (Calendar.getInstance().get(Calendar.YEAR) - this.substring(0, 4).toInt())
    } catch (e: Exception) {
        0
    }
}
