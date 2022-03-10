package com.sina.cinamovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.request.ImageRequest
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.ui.Scaffold
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sina.cinamovie.ui.content.chart.ChartScreen
import com.sina.cinamovie.ui.content.home.HomeScreen
import com.sina.cinamovie.ui.content.search.SearchScreen
import com.sina.cinamovie.ui.navigation.BottomNavItem
import com.sina.cinamovie.ui.theme.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CinaMovieTheme {
                ProvideWindowInsets(false) {
                    MainScreenView()
                }
            }
        }
    }

    @Composable
    fun MainScreenView() {

        val navController = rememberNavController()

        Box(
            modifier = Modifier
                .background(colorBlack)
                .systemBarsPadding()
        ) {

            Scaffold (
                bottomBar = { BottomNavigation(navController = navController) }
            ) {

                val systemUiController = rememberSystemUiController()

                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = false
                )

                NavigationGraph(navController = navController)

            }

        }

    }

    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
            composable(BottomNavItem.Home.screen_route) {
                HomeScreen()
            }
            composable(BottomNavItem.Search.screen_route) {
                SearchScreen()
            }
            composable(BottomNavItem.Chart.screen_route) {
                ChartScreen()
            }
        }
    }

    @Composable
    fun BottomNavigation(navController: NavController) {

        val items = listOf(
            BottomNavItem.Home ,
            BottomNavItem.Search ,
            BottomNavItem.Chart
        )

        com.google.accompanist.insets.ui.BottomNavigation(
            backgroundColor = colorGray,
            contentColor = colorWhite
        ) {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
                    selected = currentRoute == item.screen_route,
                    selectedContentColor = colorWhite,
                    unselectedContentColor = colorTextGray,
                    alwaysShowLabel = false,
                    onClick = {
                        navController.navigate(item.screen_route) {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
                            launchSingleTop = true
                            restoreState = true

//                            navController.graph.startDestinationRoute?.let { screen_route ->
//                                popUpTo(screen_route) {
//                                    saveState = true
//                                }
//                            }
//                            launchSingleTop = true
//                            restoreState = true
                        }
                    }
                )
            }

        }

    }

}
