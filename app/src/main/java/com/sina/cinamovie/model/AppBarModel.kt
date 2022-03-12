package com.sina.cinamovie.model

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.sina.cinamovie.R

data class AppBarModel(
    var title: String = "" ,
    var subTitle: String = "" ,
    var backIcon: MenuModel? = null ,
    var showBackIcon: Boolean = true ,
    var menuList: List<MenuModel> = listOf()
)
