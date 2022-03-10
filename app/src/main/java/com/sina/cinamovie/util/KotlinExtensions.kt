package com.sina.cinamovie.util

import android.content.Context
import android.util.TypedValue
import java.security.AccessController.getContext

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