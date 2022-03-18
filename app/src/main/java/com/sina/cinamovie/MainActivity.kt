package com.sina.cinamovie

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.ui.Scaffold
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sina.cinamovie.ui.content.chart.ChartScreen
import com.sina.cinamovie.ui.content.home.HomeScreen
import com.sina.cinamovie.ui.content.home.MovieScreen
import com.sina.cinamovie.ui.content.search.SearchScreen
import com.sina.cinamovie.ui.navigation.BottomNavItem
import com.sina.cinamovie.ui.theme.*
import com.sina.cinamovie.util.stackblur.StackBlurManager


class MainActivity : ComponentActivity() {

    lateinit var stackBlurManager: StackBlurManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.money_heist_cover)

        stackBlurManager = StackBlurManager(bitmap)

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

        val blurBitmap =  stackBlurManager.process(100)

        Image(
            modifier = Modifier.fillMaxSize(),
            bitmap = blurBitmap.asImageBitmap(),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(
                    listOf(
                        colorBlurBackground ,
                        colorBlack.copy(alpha = 0.75f) ,
                        colorBlurBackground
                    )
                ))
        )

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .background(Color.Transparent)
        ) {

            val (bottomNavigation , navigationGraph) = createRefs()

            val systemUiController = rememberSystemUiController()

            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = false
            )

            BottomNavigation(
                modifier = Modifier
                    .constrainAs(bottomNavigation) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    } ,
                navController = navController
            )

            NavigationGraph(
                modifier = Modifier
                    .constrainAs(navigationGraph) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(bottomNavigation.top)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    } ,
                navController = navController
            )

        }

    }

    @Composable
    fun NavigationGraph(modifier: Modifier , navController: NavHostController) {
        NavHost(
            modifier = modifier ,
            navController = navController ,
            startDestination = BottomNavItem.Home.screen_route
        ) {
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
    fun BottomNavigation(modifier: Modifier , navController: NavController) {

        val items = listOf(
            BottomNavItem.Home ,
            BottomNavItem.Search ,
            BottomNavItem.Chart
        )

        com.google.accompanist.insets.ui.BottomNavigation(
            modifier = modifier ,
            backgroundColor = colorBlack.copy(alpha = 0.75f),
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
