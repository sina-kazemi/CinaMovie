package com.sina.cinamovie.util

import android.content.Context
import android.util.TypedValue
import java.lang.Exception
import java.security.AccessController.getContext
import java.text.SimpleDateFormat
import java.util.*

//get default value for when a value is null
fun Int?.orDefault() = this ?: 0
fun Long?.orDefault() = this ?: 0L
fun Float?.orDefault() = this ?: 0f
fun Double?.orDefault() = this ?: 0
fun String?.orDefault() = this ?: ""
fun Boolean?.orDefault() = this ?: false

fun Int.toDp(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    context.resources.displayMetrics)
    .toInt()

fun Float.toDp(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this ,
    context.resources.displayMetrics)

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

//fun String.getOriginalImageSizeUrl():String {
//
//    val indexList = Regex("\\.", RegexOption.IGNORE_CASE).findAll(this).map { it.range.first }.toList()
//
//    return try {
//        this.replaceRange(indexList[indexList.size - 2] , indexList[indexList.size - 1], "")
//    } catch (e: Exception) {
//        this
//    }
//
//}

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
        val originalUrl = this.getOriginalImageSizeUrl()

        val urlWithoutJPG = originalUrl.replace(".jpg", "")

        return "$urlWithoutJPG.UX$width.jpg"
    }

    return this
}

fun String.getCustomImageHeightUrl(height: Int): String {

    if (this.isNotEmpty()) {
        val originalUrl = this.getOriginalImageSizeUrl()

        val urlWithoutJPG = originalUrl.replace(".jpg", "")

        return "$urlWithoutJPG.UY$height.jpg"
    }

    return this
}
