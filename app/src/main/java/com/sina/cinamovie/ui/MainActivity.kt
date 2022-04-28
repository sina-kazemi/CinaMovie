package com.sina.cinamovie.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.view.WindowCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sina.cinamovie.R
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.model.MovieModel
import com.sina.cinamovie.ui.content.Person.PersonScreen
import com.sina.cinamovie.ui.content.main.chart.ChartScreen
import com.sina.cinamovie.ui.content.main.home.HomeScreen
import com.sina.cinamovie.ui.content.main.search.SearchScreen
import com.sina.cinamovie.ui.content.movie.MovieScreen
import com.sina.cinamovie.ui.content.movielist.MovieListScreen
import com.sina.cinamovie.ui.navigation.BottomNavItem
import com.sina.cinamovie.ui.navigation.MainBottomNavItem
import com.sina.cinamovie.ui.theme.*
import com.sina.cinamovie.util.ITEM_ID
import com.sina.cinamovie.util.stackblur.StackBlurManager
import com.sina.cinamovie.vm.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var stackBlurManager: StackBlurManager

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.money_heist_cover)

        stackBlurManager = StackBlurManager(bitmap)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.homeUiState.collect{
                    when (it.status) {
                        Result.Status.LOADING -> {
                            Timber.d("FirstGetData::LOADING")
                        }
                        Result.Status.SUCCESS -> {
                            Timber.d("FirstGetData::SUCCESS ${it.data.toString()}")
                        }
                        Result.Status.ERROR -> {
                            Timber.d("FirstGetData::ERROR")
                        }
                    }
                }
            }
        }

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
                .background(color = colorBlack.copy(alpha = 0.85f))
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
            startDestination = MainBottomNavItem.Home.screen_route
        ) {
            composable(MainBottomNavItem.Home.screen_route) {
                HomeScreen(navController = navController , homeViewModel = homeViewModel)
            }
            composable(MainBottomNavItem.Search.screen_route) {
                SearchScreen(navController = navController)
            }
            composable(MainBottomNavItem.Chart.screen_route) {
                ChartScreen()
            }
            composable("${BottomNavItem.Movie.screen_route}/{$ITEM_ID}") { backStackEntry ->
                val itemId = backStackEntry.arguments?.getString(ITEM_ID)
                MovieScreen(itemId = itemId?: "" , navController = navController)
            }
            composable("${BottomNavItem.Person.screen_route}/{$ITEM_ID}") { backStackEntry ->
                val itemId = backStackEntry.arguments?.getString(ITEM_ID)
                PersonScreen(itemId = itemId?: "" , navController = navController)
            }
            composable(BottomNavItem.MovieList.screen_route) {
                MovieListScreen(title = "TestTitle", items = listOf(
                    MovieModel(
                        cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg" ,
                        title = "The Batman" ,
                        rate = 8.5f
                    ) ,
                    MovieModel(
                        cover = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                        title = "Spider-Man: No way home" ,
                        rate = 7.6f
                    ) ,
                    MovieModel(
                        cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg" ,
                        title = "The Batman" ,
                        rate = 8.5f
                    ) ,
                    MovieModel(
                        cover = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                        title = "Spider-Man: No way home" ,
                        rate = 7.6f
                    ) ,
                    MovieModel(
                        cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg" ,
                        title = "The Batman" ,
                        rate = 8.5f
                    ) ,
                    MovieModel(
                        cover = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                        title = "Spider-Man: No way home" ,
                        rate = 7.6f
                    ) ,
                    MovieModel(
                        cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg" ,
                        title = "The Batman" ,
                        rate = 8.5f
                    ) ,
                    MovieModel(
                        cover = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                        title = "Spider-Man: No way home" ,
                        rate = 7.6f
                    )
                ), navController = navController)
            }
        }
    }

    @Composable
    fun BottomNavigation(modifier: Modifier , navController: NavController) {

        val items = listOf(
            MainBottomNavItem.Home ,
            MainBottomNavItem.Search ,
            MainBottomNavItem.Chart
        )

        com.google.accompanist.insets.ui.BottomNavigation(
            modifier = modifier ,
            backgroundColor = colorBlack.copy(alpha = 0.75f),
            contentColor = colorWhite
        ) {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            var selectedTab by remember {
                mutableStateOf("")
            }

            if (selectedTab == "" && currentRoute != null) {
                selectedTab = currentRoute
            }

            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
                    selected = selectedTab == item.screen_route,
                    selectedContentColor = colorWhite,
                    unselectedContentColor = colorTextGray,
                    alwaysShowLabel = true,
                    label = {
                        Text(
                            text = item.title ,
                            style = TextStyle(
                                fontSize = 12.sp ,
                                fontFamily = outfitFont ,
                                fontWeight = FontWeight.Normal)
                        )
                    } ,
                    onClick = {
                        selectedTab = item.screen_route
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
