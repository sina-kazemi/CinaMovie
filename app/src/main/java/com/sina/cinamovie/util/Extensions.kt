package com.sina.cinamovie.util

import timber.log.Timber
import java.lang.Exception
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.getAge(): Int {
    return try {
        (Calendar.getInstance().get(Calendar.YEAR) - this.substring(0, 4).toInt())
    } catch (e: Exception) {
        0
    }
}

fun String.standardDateFormat(): String {
    return try {
        val formatterIn = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        val formatterOut = SimpleDateFormat("dd MMM yyyy" , Locale.ENGLISH)
        val date: Date = formatterIn.parse(this) as Date
        formatterOut.format(date)
    }
    catch (e: Exception) {
        this
    }
}


fun String.getOriginalImageSizeUrl(): String {

    if (this.isNotEmpty()) {
        return try {
            val urlWithoutJPG = this.replace(".jpg", "")
            val baseUrl = urlWithoutJPG.substring(0,urlWithoutJPG.lastIndexOf("."))
            "$baseUrl.jpg"

        }catch (ex: Exception){
            this
        }
    }

    return this
}

fun String.getCustomImageWidthUrl(width: Int): String {

    if (this.isNotEmpty()) {
        val originalUrl = getOriginalImageSizeUrl()

        val urlWithoutJPG = originalUrl.replace(".jpg", "")

        return "$urlWithoutJPG.UX$width.jpg"
    }

    return this
}

fun String.getCustomImageHeightUrl(height: Int): String {

    if (this.isNotEmpty()) {
        val originalUrl = getOriginalImageSizeUrl()

        val urlWithoutJPG = originalUrl.replace(".jpg", "")

        return "$urlWithoutJPG.UY$height.jpg"
    }

    return this
}

