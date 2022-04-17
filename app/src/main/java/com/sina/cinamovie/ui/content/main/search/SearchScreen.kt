package com.sina.cinamovie.ui.content.main.search

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.sina.cinamovie.R
import com.sina.cinamovie.model.GenreModel
import com.sina.cinamovie.model.MovieModel
import com.sina.cinamovie.model.UserModel
import com.sina.cinamovie.ui.theme.*
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SearchScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {

        val pages = listOf(
            "Genre" , "Celebs" , "Titles" , "KeyWords"
        )
        val density = LocalDensity.current
        val pagerState = rememberPagerState()
        val scope = rememberCoroutineScope()
        val tabWidths = remember {
            val tabWidthStateList = mutableStateListOf<Dp>()
            repeat(pages.size) {
                tabWidthStateList.add(0.dp)
            }
            tabWidthStateList
        }

        Spacer(modifier = Modifier.size(24.dp))

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 24.dp)
                .background(
                    shape = RoundedCornerShape(16.dp),
                    color = colorGray.copy(alpha = 0.75f)
                )
                .clickable {


                }
        ) {

            val (icon , textParent) = createRefs()

            Image(
                modifier = Modifier
                    .width(56.dp)
                    .height(56.dp)
                    .padding(16.dp)
                    .constrainAs(icon) {
                        start.linkTo(parent.start)
                    },
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = stringResource(R.string.str_search),
                colorFilter = ColorFilter.tint(colorWhite)
            )

            Box(
                modifier = Modifier
                    .constrainAs(textParent) {
                        start.linkTo(icon.end)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    } ,
                contentAlignment = Alignment.CenterStart
            ) {

                Text(
                    text = stringResource(id = R.string.str_search) ,
                    style = regularFont(16.sp , colorTextGray)
                )

            }

        }

        Spacer(modifier = Modifier.size(16.dp))

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->

                val currentTabWidth by animateDpAsState(
                    targetValue = tabWidths[pagerState.currentPage],
                    animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
                )
                val indicatorOffset by animateDpAsState(
                    targetValue = (
                            (tabPositions[pagerState.currentPage].left +
                            tabPositions[pagerState.currentPage].right -
                            tabWidths[pagerState.currentPage]) / 2),
                    animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
                )

                Box(

                    Modifier
                        .wrapContentSize(Alignment.BottomStart)
                        .offset(x = indicatorOffset)
                        .width(currentTabWidth)
                        .height(4.dp)
                        .background(
                            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                            color = colorYellow
                        )
                ){

                }
            } ,
            backgroundColor = Color.Transparent ,
        ) {
            pages.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title ,
                            style = TextStyle(
                                fontSize = 14.sp ,
                                fontFamily = outfitFont ,
                                fontWeight = FontWeight.Normal
                            ) ,
                            onTextLayout = { textLayoutResult ->

                                tabWidths[index] =
                                    with(density) { textLayoutResult.size.width.toDp() }

                            }
                        )
                    },
                    selected = pagerState.currentPage == index,
                    selectedContentColor = colorWhite ,
                    unselectedContentColor = colorTextGray2 ,
                    onClick = {
                        scope.launch { pagerState.animateScrollToPage(index) }
                    }
                )
            }
        }

        Divider(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(colorDivider))

        HorizontalPager(
            count = pages.size,
            state = pagerState,
        ) { page ->
            // TODO: page content
            Timber.d("PAGE_SWIPE:: $page")

            when (page) {
                0 -> {
                    GenreScreen(genreList = listOf(
                        GenreModel(image = "https://m.media-amazon.com/images/G/01/IMDb/genres/Comedy._CB1513316167_SX233_CR0,0,233,131_AL_.jpg") ,
                        GenreModel(image = "https://m.media-amazon.com/images/G/01/IMDb/genres/Comedy._CB1513316167_SX233_CR0,0,233,131_AL_.jpg") ,
                        GenreModel(image = "https://m.media-amazon.com/images/G/01/IMDb/genres/Comedy._CB1513316167_SX233_CR0,0,233,131_AL_.jpg") ,
                        GenreModel(image = "https://m.media-amazon.com/images/G/01/IMDb/genres/Comedy._CB1513316167_SX233_CR0,0,233,131_AL_.jpg") ,
                        GenreModel(image = "https://m.media-amazon.com/images/G/01/IMDb/genres/Comedy._CB1513316167_SX233_CR0,0,233,131_AL_.jpg") ,
                        GenreModel(image = "https://m.media-amazon.com/images/G/01/IMDb/genres/Comedy._CB1513316167_SX233_CR0,0,233,131_AL_.jpg") ,
                        GenreModel(image = "https://m.media-amazon.com/images/G/01/IMDb/genres/Comedy._CB1513316167_SX233_CR0,0,233,131_AL_.jpg")
                    ) ,
                        navController = navController
                    )
                }
                1 -> {
                    CelebsScreen(userList = listOf(
                        UserModel(
                            avatar = "https://m.media-amazon.com/images/M/MV5BMjMwMzE1OTc0OF5BMl5BanBnXkFtZTcwMDU2NTg0Nw@@._V1_UY418_CR0,0,0,0_AL_.jpg" ,
                            name = "Paul Dano" ,
                            summary = "An actor for all seasons and all kinds of roles (from dark, difficult characters to more loving ones) Paul Dano has an extensive body work that includes working with directors such as Paul Thomas Anderson, Steve McQueen, Dayton & Ferris, Ang Lee, Denis Villenueve and Paolo Sorrentino; acting with ... McQueen, Dayton & Ferris, Ang Lee, Denis Villenueve and Paolo Sorrentino; acting with ..."
                        ) ,
                        UserModel(
                            avatar = "https://m.media-amazon.com/images/M/MV5BMjMwMzE1OTc0OF5BMl5BanBnXkFtZTcwMDU2NTg0Nw@@._V1_UY418_CR0,0,0,0_AL_.jpg" ,
                            name = "Paul Dano" ,
                            summary = "An actor for all seasons and all kinds of roles (from dark, difficult characters to more loving ones) Paul Dano has an extensive body work that includes working with directors such as Paul Thomas Anderson, Steve McQueen, Dayton & Ferris, Ang Lee, Denis Villenueve and Paolo Sorrentino; acting with ..."
                        ) ,
                        UserModel(
                            avatar = "https://m.media-amazon.com/images/M/MV5BMjMwMzE1OTc0OF5BMl5BanBnXkFtZTcwMDU2NTg0Nw@@._V1_UY418_CR0,0,0,0_AL_.jpg" ,
                            name = "Paul Dano" ,
                            summary = "An actor for all seasons and all kinds of roles (from dark, difficult characters to more loving ones) Paul Dano has an extensive body work that includes working with directors such as Paul Thomas Anderson, Steve McQueen, Dayton & Ferris, Ang Lee, Denis Villenueve and Paolo Sorrentino; acting with ..."
                        ) ,
                        UserModel(
                            avatar = "https://m.media-amazon.com/images/M/MV5BMjMwMzE1OTc0OF5BMl5BanBnXkFtZTcwMDU2NTg0Nw@@._V1_UY418_CR0,0,0,0_AL_.jpg" ,
                            name = "Paul Dano" ,
                            summary = "An actor for all seasons and all kinds of roles (from dark, difficult characters to more loving ones) Paul Dano has an extensive body work that includes working with directors such as Paul Thomas Anderson, Steve McQueen, Dayton & Ferris, Ang Lee, Denis Villenueve and Paolo Sorrentino; acting with ..."
                        ) ,
                        UserModel(
                            avatar = "https://m.media-amazon.com/images/M/MV5BMjMwMzE1OTc0OF5BMl5BanBnXkFtZTcwMDU2NTg0Nw@@._V1_UY418_CR0,0,0,0_AL_.jpg" ,
                            name = "Paul Dano" ,
                            summary = "An actor for all seasons and all kinds of roles (from dark, difficult characters to more loving ones) Paul Dano has an extensive body work that includes working with directors such as Paul Thomas Anderson, Steve McQueen, Dayton & Ferris, Ang Lee, Denis Villenueve and Paolo Sorrentino; acting with ..."
                        ) ,
                        UserModel(
                            avatar = "https://m.media-amazon.com/images/M/MV5BMjMwMzE1OTc0OF5BMl5BanBnXkFtZTcwMDU2NTg0Nw@@._V1_UY418_CR0,0,0,0_AL_.jpg" ,
                            name = "Paul Dano" ,
                            summary = "An actor for all seasons and all kinds of roles (from dark, difficult characters to more loving ones) Paul Dano has an extensive body work that includes working with directors such as Paul Thomas Anderson, Steve McQueen, Dayton & Ferris, Ang Lee, Denis Villenueve and Paolo Sorrentino; acting with ..."
                        ) ,
                        UserModel(
                            avatar = "https://m.media-amazon.com/images/M/MV5BMjMwMzE1OTc0OF5BMl5BanBnXkFtZTcwMDU2NTg0Nw@@._V1_UY418_CR0,0,0,0_AL_.jpg" ,
                            name = "Paul Dano" ,
                            summary = "An actor for all seasons and all kinds of roles (from dark, difficult characters to more loving ones) Paul Dano has an extensive body work that includes working with directors such as Paul Thomas Anderson, Steve McQueen, Dayton & Ferris, Ang Lee, Denis Villenueve and Paolo Sorrentino; acting with ..."
                        )
                    ) ,
                    navController = navController)
                }
                2 -> {
                    TitlesScreen(userList = listOf(
                        MovieModel(
                            title = "The Batman" ,
                            year = "(2022)" ,
                            cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_UY392_CR0,0,0,0_AL_.jpg" ,
                            voteCount = 631862 ,
                            rate = 8.3f ,
                            summary = "When the Riddler, a sadistic serial killer, begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement"
                        ) ,
                        MovieModel(
                            title = "The Batman" ,
                            year = "(2022)" ,
                            cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_UY392_CR0,0,0,0_AL_.jpg" ,
                            voteCount = 631862 ,
                            rate = 8.3f ,
                            summary = "When the Riddler, a sadistic serial killer, begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement"
                        ) ,
                        MovieModel(
                            title = "The Batman" ,
                            year = "(2022)" ,
                            cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_UY392_CR0,0,0,0_AL_.jpg" ,
                            voteCount = 631862 ,
                            rate = 8.3f ,
                            summary = "When the Riddler, a sadistic serial killer, begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement"
                        ) ,
                        MovieModel(
                            title = "The Batman" ,
                            year = "(2022)" ,
                            cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_UY392_CR0,0,0,0_AL_.jpg" ,
                            voteCount = 631862 ,
                            rate = 8.3f ,
                            summary = "When the Riddler, a sadistic serial killer, begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement"
                        ) ,
                        MovieModel(
                            title = "The Batman" ,
                            year = "(2022)" ,
                            cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_UY392_CR0,0,0,0_AL_.jpg" ,
                            voteCount = 631862 ,
                            rate = 8.3f ,
                            summary = "When the Riddler, a sadistic serial killer, begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement"
                        ) ,
                        MovieModel(
                            title = "The Batman" ,
                            year = "(2022)" ,
                            cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_UY392_CR0,0,0,0_AL_.jpg" ,
                            voteCount = 631862 ,
                            rate = 8.3f ,
                            summary = "When the Riddler, a sadistic serial killer, begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement"
                        )
                    ) ,
                    navController = navController)
                }
                3 -> {
                    KeyWordsScreen(wordsList = listOf(
                        "Alternate history 1" ,
                        "Alternate history 2" ,
                        "Alternate history 3" ,
                        "Alternate history 4" ,
                        "Alternate history 5" ,
                        "Alternate history 6" ,
                        "Alternate history 7" ,
                        "Alternate history 8" ,
                        "Alternate history 9" ,
                        "Alternate history 10" ,
                        "Alternate history 11" ,
                        "Alternate history 12" ,
                        "Alternate history 13" ,
                        "Alternate history 14" ,
                        "Alternate history 15"
                    ))
                }
            }

        }

    }
}
