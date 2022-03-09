package com.sina.cinamovie.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.platform.LocalContext
import com.sina.cinamovie.R

sealed class BottomNavItem(
    var title:String,
    var icon:Int,
    var screen_route:String
) {
    object Home : BottomNavItem("Home" , R.drawable.ic_home , "home")
    object Search: BottomNavItem("Search" , R.drawable.ic_search , "search")
    object Chart: BottomNavItem("Chart" , R.drawable.ic_chart , "chart")
}
