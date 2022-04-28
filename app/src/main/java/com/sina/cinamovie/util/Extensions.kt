package com.sina.cinamovie.util

import java.util.*

fun String.getAge(): Int {
    return (Calendar.getInstance().get(Calendar.YEAR) - this.substring(0 , 4).toInt())
}