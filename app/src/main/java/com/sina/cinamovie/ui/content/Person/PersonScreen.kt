package com.sina.cinamovie.ui.content.Person

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.sina.cinamovie.R
import com.sina.cinamovie.model.*
import com.sina.cinamovie.ui.content.common.AppBar
import com.sina.cinamovie.ui.content.common.ListHeader
import com.sina.cinamovie.ui.content.list.*
import com.sina.cinamovie.ui.theme.*
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PersonScreen(itemId: String , navController: NavHostController) {

    Timber.d("PersonId:: $itemId")

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        AppBar(
            model = AppBarModel(
                title = "Matthew McConaughey"
            ) ,
            navController = navController
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            ConstraintLayout (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, top = 8.dp)
            ) {

                val guideline = createGuidelineFromStart(fraction = 0.666f)
                val (imageParent , detailParent) = createRefs()

                Box(
                    modifier = Modifier
                        .constrainAs(imageParent) {
                            start.linkTo(parent.start)
                            end.linkTo(guideline)
                            top.linkTo(parent.top)
                            width = Dimension.fillToConstraints
                        }
                        .aspectRatio(2f / 3f)
                        .padding(12.dp)
                        .background(
                            shape = RoundedCornerShape(16.dp),
                            color = colorGray.copy(alpha = 0.75f)
                        )
                ) {

                    AsyncImage(
                        modifier = Modifier.clip(shape = RoundedCornerShape(16.dp)) ,
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data("https://m.media-amazon.com/images/M/MV5BMTg0MDc3ODUwOV5BMl5BanBnXkFtZTcwMTk2NjY4Nw@@._V1_.jpg")
                            .crossfade(true)
                            .build(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )

                }

                ConstraintLayout (
                    modifier = Modifier
                        .constrainAs(detailParent) {
                            end.linkTo(parent.end)
                            top.linkTo(imageParent.top)
                            start.linkTo(guideline)
                            bottom.linkTo(imageParent.bottom)
                            width = Dimension.fillToConstraints
                        }
                        .aspectRatio(1f / 3f)
                ) {

                    val detailGuideLine = createGuidelineFromTop(0.5f)

                    val (jobParent , ageParent) = createRefs()

                    Column(
                        modifier = Modifier
                            .constrainAs(jobParent) {
                                top.linkTo(parent.top)
                                bottom.linkTo(detailGuideLine)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                                height = Dimension.fillToConstraints
                            }
                            .padding(12.dp)
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = colorGray.copy(alpha = 0.75f)
                            ) ,
                        horizontalAlignment = Alignment.CenterHorizontally ,
                        verticalArrangement = Arrangement.Center
                    ) {

                        DetailPersonHeader(
                            painter = painterResource(id = R.drawable.ic_camera_movie),
                            title = stringResource(R.string.str_job),
                            desc = "Actor"
                        )

                    }

                    Column(
                        modifier = Modifier
                            .constrainAs(ageParent) {
                                top.linkTo(detailGuideLine)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                                height = Dimension.fillToConstraints
                            }
                            .padding(12.dp)
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = colorGray.copy(alpha = 0.75f)
                            ) ,
                        horizontalAlignment = Alignment.CenterHorizontally ,
                        verticalArrangement = Arrangement.Center
                    ) {

                        DetailPersonHeader(
                            painter = painterResource(id = R.drawable.ic_user_detail),
                            title = stringResource(R.string.str_age),
                            desc = "41 Years"
                        )

                    }

                }

            }

            Spacer(modifier = Modifier.size(40.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp) ,
                text = "Matthew McConaughey" ,
                style = mediumFont(24.sp) ,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.size(12.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp) ,
                text = "An unusual group of robbers attempt to carry out the most perfect robbery in Spanish history - stealing 2.4 billion euros from the Royal Mint of Spain.An unusual group of robbers attempt to carry out the most perfect robbery in Spanish history - stealing 2.4 billion euros from the Royal Mint of Spain.An unusual group of robbers attempt to carry out the most perfect robbery in Spanish history - stealing 2.4 billion euros from the Royal Mint of Spain." ,
                style = regularFont(textColor = colorTextGray3) ,
                lineHeight = 24.sp ,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.size(16.dp))

            GenreList(genreList = listOf(
                GenreModel(genre = "Actor") ,
                GenreModel(genre = "Producer") ,
                GenreModel(genre = "Director")
            ))

            val screenWidth = LocalConfiguration.current.screenWidthDp.dp
            var heightSize by remember { mutableStateOf(IntSize.Zero) }

            Spacer(modifier = Modifier.size(48.dp))
            ListHeader(title = stringResource(R.string.str_photos_and_trailer) , true)
            Spacer(modifier = Modifier.size(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()) ,
                verticalAlignment = Alignment.CenterVertically ,
                horizontalArrangement = Arrangement.Start
            ) {

                Spacer(modifier = Modifier.size(24.dp))

                ConstraintLayout (
                    modifier = Modifier
                        .onSizeChanged {
                            heightSize = it
                        }
                ) {

                    Box(
                        modifier = Modifier
                            .width(screenWidth / 3)
                            .aspectRatio(2f / 3f)
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = colorGray.copy(alpha = 0.75f)
                            )
                    ) {

                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = RoundedCornerShape(16.dp)) ,
                            model = ImageRequest
                                .Builder(LocalContext.current)
                                .data("https://m.media-amazon.com/images/M/MV5BMTg0MDc3ODUwOV5BMl5BanBnXkFtZTcwMTk2NjY4Nw@@._V1_.jpg")
                                .crossfade(true)
                                .build(),
                            contentDescription = "" ,
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    shape = RoundedCornerShape(16.dp),
                                    color = colorBlack.copy(alpha = 0.75f)
                                ) ,
                            contentAlignment = Alignment.Center
                        ) {

                            Text(text = "Play Trailer" , style = mediumFont(16.sp))

                        }

                    }

                }

                Spacer(modifier = Modifier.size(16.dp))

                Row (
                    modifier = Modifier
                        .height(with(LocalDensity.current) { heightSize.height.toDp() })
                        .fillMaxWidth() ,
                    horizontalArrangement = Arrangement.spacedBy(16.dp) ,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    listOf(
                        ImageModel(original = "https://m.media-amazon.com/images/M/MV5BMTM0NjUxMDk5MF5BMl5BanBnXkFtZTcwNDMxNDY3Mw@@._V1_.jpg") ,
                        ImageModel(original = "https://m.media-amazon.com/images/M/MV5BMTk3NDE2Nzg3Nl5BMl5BanBnXkFtZTcwNTMxNDY3Mw@@._V1_.jpg") ,
                        ImageModel(original = "https://m.media-amazon.com/images/M/MV5BMTg0MDgwNjc5N15BMl5BanBnXkFtZTcwNjMxNDY3Mw@@._V1_.jpg") ,
                        ImageModel(original = "https://m.media-amazon.com/images/M/MV5BMTkzMTY0MjE5MV5BMl5BanBnXkFtZTcwODMxNDY3Mw@@._V1_.jpg") ,
                        ImageModel(original = "https://m.media-amazon.com/images/M/MV5BMTg0MDgwNjc5N15BMl5BanBnXkFtZTcwNjMxNDY3Mw@@._V1_.jpg") ,
                        ImageModel(original = "https://m.media-amazon.com/images/M/MV5BMTkzMTY0MjE5MV5BMl5BanBnXkFtZTcwODMxNDY3Mw@@._V1_.jpg") ,
                        ImageModel(original = "https://m.media-amazon.com/images/M/MV5BMTg0MDgwNjc5N15BMl5BanBnXkFtZTcwNjMxNDY3Mw@@._V1_.jpg") ,
                        ImageModel(original = "https://m.media-amazon.com/images/M/MV5BMTkzMTY0MjE5MV5BMl5BanBnXkFtZTcwODMxNDY3Mw@@._V1_.jpg") ,
                        ImageModel(original = "https://m.media-amazon.com/images/M/MV5BNTYxOTYyMzE3NV5BMl5BanBnXkFtZTcwOTMxNDY3Mw@@._V1_.jpg") ,
                        ImageModel(original = "https://m.media-amazon.com/images/M/MV5BMTgxMTU1MDkwOV5BMl5BanBnXkFtZTcwMDQxNDY3Mw@@._V1_.jpg")
                    ).windowed(2 , 2 , true).forEach { subList ->

                        Column (
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            subList.forEach { item ->

                                AsyncImage(
                                    modifier = Modifier
                                        .aspectRatio(1f / 1f)
                                        .weight(1f)
                                        .clip(shape = RoundedCornerShape(16.dp))
                                        .background(
                                            shape = RoundedCornerShape(16.dp),
                                            color = colorGray.copy(alpha = 0.75f)
                                        ),
                                    model = ImageRequest
                                        .Builder(LocalContext.current)
                                        .data(item.original)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop
                                )

                            }

                            repeat(2 - subList.size) {

                                Box(
                                    modifier = Modifier
                                        .aspectRatio(1f / 1f)
                                        .clip(shape = RoundedCornerShape(16.dp))
                                        .background(
                                            shape = RoundedCornerShape(16.dp),
                                            color = colorGray.copy(alpha = 0.75f)
                                        )
                                ) {

                                }

                            }

                        }

                    }

                }

                Spacer(modifier = Modifier.size(16.dp))

            }

            Spacer(modifier = Modifier.size(48.dp))
            ListHeader(title = stringResource(R.string.str_known_for) , true)
            Spacer(modifier = Modifier.size(24.dp))
            MovieList(movieList = listOf(
                MovieModel(
                    cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg" ,
                    title = "The Batman" ,
                    subTitle = "Peter Parker"
                ) ,
                MovieModel(
                    cover = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                    title = "Spider-Man: No way home" ,
                    subTitle = "Peter Parker"
                ) ,
                MovieModel(
                    cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg" ,
                    title = "The Batman" ,
                    subTitle = "Peter Parker"
                ) ,
                MovieModel(
                    cover = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                    title = "Spider-Man: No way home" ,
                    subTitle = "Peter Parker"
                ) ,
                MovieModel(
                    cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg" ,
                    title = "The Batman" ,
                    subTitle = "Peter Parker"
                ) ,
                MovieModel(
                    cover = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                    title = "Spider-Man: No way home" ,
                    subTitle = "Peter Parker"
                ) ,
                MovieModel(
                    cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg" ,
                    title = "The Batman" ,
                    subTitle = "Peter Parker"
                ) ,
                MovieModel(
                    cover = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                    title = "Spider-Man: No way home" ,
                    subTitle = "Peter Parker"
                ) ,
            ) , navController = navController)

            Spacer(modifier = Modifier.size(48.dp))
            ListHeader(title = stringResource(R.string.str_details) , false)
            Spacer(modifier = Modifier.size(24.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .background(
                        shape = RoundedCornerShape(16.dp),
                        color = colorGray.copy(alpha = 0.75f)
                    )
            ) {

                DetailMovieColumnItem(title = stringResource(R.string.str_released_date), desc = "May 2, 2017")
                DetailMovieColumnItem(title = stringResource(R.string.str_country), desc = "Italy")
                DetailMovieColumnItem(title = stringResource(R.string.str_locations), desc = "Florence, Italy")
                DetailMovieColumnItem(title = stringResource(R.string.str_languages), desc = "Spanish . Russian . Serbian . English")
                DetailMovieColumnItem(title = stringResource(R.string.str_companies), desc = "Atresmedia . Vancouver Media" , false)

            }

            Spacer(modifier = Modifier.size(48.dp))
            ListHeader(title = stringResource(R.string.str_filmography) , false)
            Spacer(modifier = Modifier.size(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {

                val pages = listOf(
                    "Actor" , "Producer" , "Director" , "Additional Crew" , "Thanks"
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

                ScrollableTabRow(
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
                    edgePadding = 0.dp
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

                    FilmographyScreen(
                        movieList = listOf(
                            MovieModel(title = "The Batman" , year = "2022" , subTitle = "Bruce Waine") ,
                            MovieModel(title = "The Batman" , year = "2022" , subTitle = "Bruce Waine") ,
                            MovieModel(title = "The Batman" , year = "2022" , subTitle = "Bruce Waine") ,
                            MovieModel(title = "The Batman" , year = "2022" , subTitle = "Bruce Waine") ,
                            MovieModel(title = "The Batman" , year = "2022" , subTitle = "Bruce Waine") ,
                            MovieModel(title = "The Batman" , year = "2022" , subTitle = "Bruce Waine") ,
                            MovieModel(title = "The Batman" , year = "2022" , subTitle = "Bruce Waine") ,
                            MovieModel(title = "The Batman" , year = "2022" , subTitle = "Bruce Waine") ,
                            MovieModel(title = "The Batman" , year = "2022" , subTitle = "Bruce Waine") ,
                            MovieModel(title = "The Batman" , year = "2022" , subTitle = "Bruce Waine")
                        ) ,
                        navController = navController
                    )

                }

            }

        }

    }

}

@Composable
private fun DetailPersonHeader(painter: Painter, title: String, desc: String) {

    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            verticalArrangement = Arrangement.Center ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp),
                painter = painter,
                contentDescription = "",
                colorFilter = ColorFilter.tint(colorWhite)
            )

            Spacer(modifier = Modifier.size(12.dp))

            Text(text = title , style = regularFont(14.sp , colorTextGray2) , maxLines = 1 , overflow = TextOverflow.Ellipsis)

            Spacer(modifier = Modifier.size(2.dp))

            Text(text = desc , style = boldFont(16.sp , colorWhite) , maxLines = 1 , overflow = TextOverflow.Ellipsis)

        }

    }

}

@Composable
private fun DetailMovieColumnItem(title: String , desc: String , showDivider: Boolean = true) {

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 18.dp) ,
            horizontalArrangement = Arrangement.SpaceBetween ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            
            Text(text = title, style = regularFont(14.sp))

            Spacer(modifier = Modifier.size(12.dp))

            Text(text = desc, style = regularFont(14.sp, colorTextGray2) , maxLines = 1 , overflow = TextOverflow.Ellipsis)
            
        }

        if (showDivider) {

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 12.dp),
                color = colorDivider
            )
            
        }

    }

}