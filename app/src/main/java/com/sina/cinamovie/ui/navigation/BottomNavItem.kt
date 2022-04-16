package com.sina.cinamovie.ui.navigation

import com.sina.cinamovie.R

sealed class BottomNavItem(
    var screen_route:String
) {
    object Movie : BottomNavItem("movieScreen")
    object Person : BottomNavItem("personScreen")
}
