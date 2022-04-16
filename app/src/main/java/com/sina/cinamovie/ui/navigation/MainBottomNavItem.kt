package com.sina.cinamovie.ui.navigation

import com.sina.cinamovie.R

sealed class MainBottomNavItem(
    var title:String,
    var icon:Int,
    var screen_route:String
) {
    object Home : MainBottomNavItem("Home" , R.drawable.ic_home , "home")
    object Search: MainBottomNavItem("Search" , R.drawable.ic_search , "search")
    object Chart: MainBottomNavItem("Chart" , R.drawable.ic_chart , "chart")
}
