package com.sina.cinamovie.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.sina.cinamovie.R

val outfitFont = FontFamily(
    Font(R.font.outfit_extralight , FontWeight.ExtraLight) ,
    Font(R.font.outfit_light , FontWeight.Light) ,
    Font(R.font.outfit_regular) ,
    Font(R.font.outfit_medium , FontWeight.Medium) ,
    Font(R.font.outfit_bold , FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

fun extraLightFont(fontSize: TextUnit = 14.sp , textColor: Color = colorWhite): TextStyle =
    TextStyle(
        color = textColor ,
        fontSize = fontSize ,
        fontFamily = outfitFont ,
        fontWeight = FontWeight.ExtraLight
    )

fun lightFont(fontSize: TextUnit = 14.sp , textColor: Color = colorWhite): TextStyle =
    TextStyle(
        color = textColor ,
        fontSize = fontSize ,
        fontFamily = outfitFont ,
        fontWeight = FontWeight.Light
    )

fun regularFont(fontSize: TextUnit = 14.sp , textColor: Color = colorWhite): TextStyle =
    TextStyle(
        color = textColor ,
        fontSize = fontSize ,
        fontFamily = outfitFont ,
        fontWeight = FontWeight.Normal
    )

fun mediumFont(fontSize: TextUnit = 14.sp , textColor: Color = colorWhite): TextStyle =
    TextStyle(
        color = textColor ,
        fontSize = fontSize ,
        fontFamily = outfitFont ,
        fontWeight = FontWeight.Medium
    )

fun boldFont(fontSize: TextUnit = 14.sp , textColor: Color = colorWhite): TextStyle =
    TextStyle(
        color = textColor ,
        fontSize = fontSize ,
        fontFamily = outfitFont ,
        fontWeight = FontWeight.Bold
    )