package com.sina.cinamovie.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.sina.cinamovie.ui.theme.colorWhite

data class MenuModel (
    var painter: Painter? = null,
    var title: String = "",
    var iconColor: Color = colorWhite ,
    var onClick: () -> Unit = {}
)