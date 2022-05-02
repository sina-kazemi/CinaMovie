package com.sina.cinamovie.ui.content.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.sina.cinamovie.R
import com.sina.cinamovie.data.res.HomeExtraRes
import com.sina.cinamovie.data.res.HomeRes
import com.sina.cinamovie.data.res.TitleDetailsRes
import com.sina.cinamovie.model.*
import com.sina.cinamovie.ui.navigation.BottomNavItem
import com.sina.cinamovie.ui.theme.*
import timber.log.Timber
import java.lang.Exception

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TrailerList(trailerList: List<HomeRes.Trailer> , showPlaceHolder: Boolean = false) {

    val pagerState = rememberPagerState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            count = trailerList.size ,
            state = pagerState ,
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) { position ->

            TrailerRow(model = trailerList[position] , showPlaceHolder)

        }

        Spacer(modifier = Modifier.size(24.dp))

        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = colorWhite,
            inactiveColor = colorTextGray,
            indicatorWidth = 6.dp,
            indicatorHeight = 6.dp,
            spacing = 3.dp
        )

    }

}

@Composable
fun TrailerRow(model: HomeRes.Trailer , showPlaceHolder: Boolean = false) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        val (mainParent) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(mainParent) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
                .aspectRatio(1f / 0.55f)
                .padding(horizontal = 8.dp)
                .background(color = colorGray, shape = RoundedCornerShape(16.dp))
                .placeholder(
                    visible = showPlaceHolder,
                    color = colorGray,
                    shape = RoundedCornerShape(16.dp),
                    highlight = PlaceholderHighlight.fade(
                        highlightColor = colorPlaceHolder
                    )
                ) ,
            contentAlignment = Alignment.BottomCenter
        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(16.dp)) ,
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(model.preview)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
            ) {

                val (playParent , descriptionParent) = createRefs()

                Box(
                    modifier = Modifier
                        .width(44.dp)
                        .height(44.dp)
                        .constrainAs(playParent) {
                            start.linkTo(parent.start)
                            bottom.linkTo(parent.bottom)
                        }
                        .background(
                            shape = RoundedCornerShape(12.dp),
                            color = colorBlack.copy(alpha = 0.5f)
                        ) ,
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                            .offset(x = 2.dp) ,
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = "",
                        contentScale = ContentScale.Inside,
                        colorFilter = ColorFilter.tint(colorWhite)
                    )

                }

                Column(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .constrainAs(descriptionParent) {
                            linkTo(
                                start = playParent.end,
                                end = parent.end,
                                top = parent.top,
                                bottom = parent.bottom
                            )
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        } ,
                    horizontalAlignment = Alignment.Start ,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = model.title!! ,
                        style = regularFont().copy(
                            shadow = Shadow(
                                color = colorBlack ,
                                offset = Offset(2f , 2f) ,
                                blurRadius = 6f
                            )
                        ) ,
                        maxLines = 1 ,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.size(2.dp))

                    Text(
                        text = model.duration!! ,
                        style = boldFont(12.sp).copy(
                            shadow = Shadow(
                                color = colorBlack ,
                                offset = Offset(2f , 2f) ,
                                blurRadius = 6f
                            )
                        ) ,
                        maxLines = 1 ,
                        overflow = TextOverflow.Ellipsis
                    )

                }

            }

        }

    }

}

@Composable
fun ImdbOriginalRow(model: HomeRes.ImdbOriginal , showPlaceHolder: Boolean = false) {

    Box(
        modifier = Modifier
            .width(320.dp)
            .aspectRatio(1f / 0.55f)
            .background(color = colorGray, shape = RoundedCornerShape(16.dp))
            .placeholder(
                visible = showPlaceHolder,
                color = colorGray,
                shape = RoundedCornerShape(16.dp),
                highlight = PlaceholderHighlight.fade(
                    highlightColor = colorPlaceHolder
                )
            ) ,
        contentAlignment = Alignment.BottomCenter
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(16.dp)) ,
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(model.cover)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
        ) {

            val (playParent , descriptionParent) = createRefs()

            Box(
                modifier = Modifier
                    .width(44.dp)
                    .height(44.dp)
                    .constrainAs(playParent) {
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
                    .background(
                        shape = RoundedCornerShape(12.dp),
                        color = colorBlack.copy(alpha = 0.5f)
                    ) ,
                contentAlignment = Alignment.Center
            ) {

                Image(
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                        .offset(x = 2.dp) ,
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "",
                    contentScale = ContentScale.Inside,
                    colorFilter = ColorFilter.tint(colorWhite)
                )

            }

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .constrainAs(descriptionParent) {
                        linkTo(
                            start = playParent.end,
                            end = parent.end,
                            top = parent.top,
                            bottom = parent.bottom
                        )
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    } ,
                horizontalAlignment = Alignment.Start ,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = model.title!! ,
                    style = regularFont().copy(
                        shadow = Shadow(
                            color = colorBlack ,
                            offset = Offset(2f , 2f) ,
                            blurRadius = 6f
                        )
                    ) ,
                    maxLines = 1 ,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.size(2.dp))

                Text(
                    text = model.caption!! ,
                    style = boldFont(12.sp).copy(
                        shadow = Shadow(
                            color = colorBlack ,
                            offset = Offset(2f , 2f) ,
                            blurRadius = 6f
                        )
                    ) ,
                    maxLines = 1 ,
                    overflow = TextOverflow.Ellipsis
                )

            }

        }

    }

}

@Composable
fun <T> MovieList(movieList: List<T> , navController: NavHostController , showPlaceHolder: Boolean = false) {

    var newMovieList: List<MovieModel> = listOf()

    if (movieList.isNotEmpty()) {
        when {
            movieList.first() is HomeExtraRes.FanPicksTitle -> {
                newMovieList = (movieList as List<HomeExtraRes.FanPicksTitle>).map {
                    MovieModel(
                        certificate = it.certificate,
                        cover = it.cover,
                        rate = it.rate,
                        releaseDay = it.releaseDay,
                        releaseMonth = it.releaseMonth,
                        releaseYear = it.releaseYear,
                        runtime = it.runtime,
                        title = it.title,
                        titleId = it.titleId,
                        videoId = it.videoId,
                        voteCount = it.voteCount
                    )
                }
            }
            movieList.first() is HomeExtraRes.ComingSoonMovie -> {
                newMovieList = (movieList as List<HomeExtraRes.ComingSoonMovie>).map {
                    MovieModel(
                        certificate = it.certificate,
                        cover = it.cover,
                        rate = it.rate,
                        releaseDay = it.releaseDay,
                        releaseMonth = it.releaseMonth,
                        releaseYear = it.releaseYear,
                        runtime = it.runtime,
                        title = it.title,
                        titleId = it.titleId,
                        videoId = it.videoId,
                        voteCount = it.voteCount
                    )
                }
            }
            movieList.first() is TitleDetailsRes.RelatedMovie -> {
                newMovieList = (movieList as List<TitleDetailsRes.RelatedMovie>).map {
                    MovieModel(
                        rate = run {
                            try {
                                it.rate?.toFloat()
                            }
                            catch (e: Exception) {
                                null
                            }
                        },
                        cover = it.cover,
                        title = it.title,
                        titleId = it.id
                    )
                }
            }
        }
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp) ,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {

        items(newMovieList) { item ->
            MovieItem(item = item, navController = navController , showPlaceHolder = showPlaceHolder)
        }

    }


}

@Composable
fun MovieItem(
    modifier: Modifier? = null ,
    item: MovieModel ,
    navController: NavHostController ,
    isGridList: Boolean = false ,
    showPlaceHolder: Boolean = false
) {

    var mModifier = Modifier.wrapContentWidth()
    mModifier = modifier?.clickable { navController.navigate("${BottomNavItem.Movie.screen_route}/${item.titleId}") }
        ?: Modifier.clickable { navController.navigate("${BottomNavItem.Movie.screen_route}/${item.titleId}") }

    ConstraintLayout(
        modifier = mModifier
    ) {

        val (imageParent , title , subTitle , rateParent , spacer1 , spacer2) = createRefs()

        Box(
            modifier = kotlin.run {
                if (isGridList) {
                    Modifier
                        .aspectRatio(2f / 3f)
                        .background(shape = RoundedCornerShape(16.dp), color = colorGray)
                        .placeholder(
                            visible = showPlaceHolder,
                            color = colorGray,
                            shape = RoundedCornerShape(16.dp),
                            highlight = PlaceholderHighlight.fade(
                                highlightColor = colorPlaceHolder
                            )
                        )
                        .constrainAs(imageParent) {

                        }
                }
                else {
                    Modifier
                        .width(96.dp)
                        .aspectRatio(2f / 3f)
                        .background(shape = RoundedCornerShape(16.dp), color = colorGray)
                        .placeholder(
                            visible = showPlaceHolder,
                            color = colorGray,
                            shape = RoundedCornerShape(16.dp),
                            highlight = PlaceholderHighlight.fade(
                                highlightColor = colorPlaceHolder
                            )
                        )
                        .constrainAs(imageParent) {

                        }
                }
            }


        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(16.dp)),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(item.cover)
                    .crossfade(true)
                    .build(),
                contentDescription = "" ,
                contentScale = ContentScale.Crop
            )

        }

        Spacer(
            modifier = Modifier
                .size(8.dp)
                .constrainAs(spacer1) {
                    top.linkTo(imageParent.bottom)
                }
        )

        Text(
            modifier = Modifier
                .placeholder(
                    visible = showPlaceHolder,
                    color = colorGray,
                    shape = RoundedCornerShape(16.dp),
                    highlight = PlaceholderHighlight.fade(
                        highlightColor = colorPlaceHolder
                    )
                )
                .constrainAs(title) {
                    top.linkTo(spacer1.bottom)
                    start.linkTo(imageParent.start)
                    end.linkTo(imageParent.end)
                    width = Dimension.fillToConstraints
                },
            text = item.title.toString(),
            style = regularFont(12.sp) ,
            maxLines = 1 ,
            overflow = TextOverflow.Ellipsis ,
            textAlign = TextAlign.Center
        )

        if (item.subTitle != null) {

            Spacer(
                modifier = Modifier
                    .size(3.dp)
                    .constrainAs(spacer2) {
                        top.linkTo(title.bottom)
                    }
            )

            Text(
                modifier = Modifier
                    .placeholder(
                        visible = showPlaceHolder,
                        color = colorGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight.fade(
                            highlightColor = colorPlaceHolder
                        )
                    )
                    .constrainAs(subTitle) {
                        top.linkTo(spacer2.bottom)
                        start.linkTo(imageParent.start)
                        end.linkTo(imageParent.end)
                        bottom.linkTo(parent.bottom)
                    } ,
                text = item.subTitle!!,
                style = regularFont(12.sp , colorTextGray2) ,
                maxLines = 1 ,
                overflow = TextOverflow.Ellipsis ,
                textAlign = TextAlign.Center
            )

        }
        else if (item.rate != null) {

            Spacer(
                modifier = Modifier
                    .size(4.dp)
                    .constrainAs(spacer2) {
                        top.linkTo(title.bottom)
                    }
            )

            Row(
                modifier = Modifier
                    .placeholder(
                        visible = showPlaceHolder,
                        color = colorGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight.fade(
                            highlightColor = colorPlaceHolder
                        )
                    )
                    .constrainAs(rateParent) {
                        top.linkTo(spacer2.bottom)
                        start.linkTo(imageParent.start)
                        end.linkTo(imageParent.end)
                        bottom.linkTo(parent.bottom)
                    } ,
                verticalAlignment = Alignment.CenterVertically ,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    modifier = Modifier.offset(y = (0.75).dp) ,
                    text = item.rate.toString() ,
                    style = boldFont(12.sp , colorYellow) ,
                )

                Spacer(modifier = Modifier.size(3.dp))

                Image(
                    modifier = Modifier
                        .width(12.dp)
                        .height(12.dp) ,
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "" ,
                    colorFilter = ColorFilter.tint(colorYellow),
                    contentScale = ContentScale.Inside
                )

            }

        }

    }

}

@Composable
fun IMDbOriginalsList(imdbOriginalList: List<HomeRes.ImdbOriginal> , showPlaceHolder: Boolean = false) {

    var titleParentSize by remember {
        mutableStateOf(IntSize.Zero)
    }
    val widthInDp = with(LocalDensity.current) { titleParentSize.width.toDp() }
    var offset by remember { mutableStateOf(0f) }
    val listState = rememberLazyListState()
    val showTitle by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0
        }
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorBlack.copy(alpha = 0.75f))
            .placeholder(
                visible = showPlaceHolder,
                color = colorGray,
                highlight = PlaceholderHighlight.fade(
                    highlightColor = colorPlaceHolder
                )
            )
    ) {

        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.CenterStart),
            visible = showTitle ,
            enter = fadeIn() ,
            exit = fadeOut()
        ) {

            Box(
                modifier = Modifier
                    .onSizeChanged { titleParentSize = it }
                    .align(Alignment.CenterStart) ,
            ) {

                Column(
                    modifier = Modifier
                        .padding(horizontal = 32.dp),
                    verticalArrangement = Arrangement.Center ,
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(text = stringResource(R.string.str_imdb), style = mediumFont(16.sp))
                    Text(text = stringResource(R.string.str_originals), style = extraLightFont(16.sp))

                }

            }

        }

        LazyRow(
            modifier = Modifier
                .scrollable(
                    orientation = Orientation.Horizontal,
                    state = ScrollableState {
                        it
                    }
                )
                .padding(vertical = 24.dp) ,
            horizontalArrangement = Arrangement.spacedBy(16.dp) ,
            contentPadding = PaddingValues(start = widthInDp , end = 16.dp) ,
            state = listState
        ) {

            items(imdbOriginalList) { item ->

                ImdbOriginalRow(model = item , showPlaceHolder = showPlaceHolder)

            }

        }

    }

}

@Composable
fun GenreList(genreList: List<TitleDetailsRes.Overview.Genre> , showPlaceHolder: Boolean = false) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp) ,
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {

        items(genreList) { item ->

            Text(
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(12.dp),
                        color = colorGray.copy(alpha = 0.75f)
                    )
                    .placeholder(
                        visible = showPlaceHolder,
                        color = colorGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight.fade(
                            highlightColor = colorPlaceHolder
                        )
                    )
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                text = item.title.toString() ,
                style = regularFont()
            )

        }

    }

}

@Composable
fun ImageList(modifier: Modifier , itemSizeDp: Dp ,imageList: List<TitleDetailsRes.Photo> , showPlaceHolder: Boolean = false) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenWidthPx = with(LocalDensity.current) { screenWidth.toPx() }.toInt()

    Row (
        modifier = modifier ,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){

        imageList.forEach {

            AsyncImage(
                modifier = Modifier
                    .width(itemSizeDp)
                    .height(itemSizeDp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(
                        shape = RoundedCornerShape(16.dp),
                        color = colorGray.copy(alpha = 0.75f)
                    )
                    .placeholder(
                        visible = showPlaceHolder,
                        color = colorGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight.fade(
                            highlightColor = colorPlaceHolder
                        )
                    ),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(it.original)
                    .crossfade(true)
                    .size(screenWidthPx/4 , screenWidthPx/4)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

        }

        Spacer(modifier = Modifier.size(8.dp))

    }

}

@Composable
fun VideoList(modifier: Modifier , itemWidthDp: Dp , itemHeightDp: Dp , videoList: List<TitleDetailsRes.Video> , showPlaceHolder: Boolean = false) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenWidthPx = with(LocalDensity.current) { screenWidth.toPx() }.toInt()

    Row (
        modifier = modifier ,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){

        videoList.forEach {

            Box(modifier = Modifier
                .fillMaxHeight()
                .width(itemWidthDp)
                .height(itemHeightDp)
                .background(
                    shape = RoundedCornerShape(16.dp),
                    color = colorGray.copy(alpha = 0.75f)
                )
                .placeholder(
                    visible = showPlaceHolder,
                    color = colorGray,
                    shape = RoundedCornerShape(16.dp),
                    highlight = PlaceholderHighlight.fade(
                        highlightColor = colorPlaceHolder
                    )
                )
            ) {

                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(16.dp)),
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(it.preview)
                        .crossfade(true)
                        .size(screenWidthPx/2 , screenWidthPx/4)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 8.dp, bottom = 8.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                            .align(Alignment.BottomStart)
                            .background(
                                shape = RoundedCornerShape(12.dp),
                                color = colorBlack.copy(alpha = 0.5f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Image(
                            modifier = Modifier
                                .width(16.dp)
                                .height(16.dp)
                                .offset(x = 2.dp) ,
                            painter = painterResource(id = R.drawable.ic_play),
                            contentDescription = "",
                            contentScale = ContentScale.Inside,
                            colorFilter = ColorFilter.tint(colorWhite)
                        )

                    }

                }

            }

        }

        Spacer(modifier = Modifier.size(8.dp))

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CastsList(castList: List<CastModel>) {

    LazyColumn() {
        
        items(castList.windowed(3 , 3, true)) { subList ->

            Row(modifier = Modifier.fillMaxWidth()) {

                subList.forEach {

                    Column(
                        modifier = Modifier.fillMaxWidth(0.5f) ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f / 1f)
                                .clip(shape = RoundedCornerShape(16.dp))
                                .background(
                                    shape = RoundedCornerShape(16.dp),
                                    color = colorGray.copy(alpha = 0.75f)
                                ),
                            model = ImageRequest
                                .Builder(LocalContext.current)
                                .data(it.image)
                                .crossfade(true)
                                .build(),
                            contentDescription = "")

                        Spacer(modifier = Modifier.size(8.dp))

                        Text(
                            text = it.realName ,
                            style = regularFont() ,
                            maxLines = 1 ,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.size(2.dp))

                        Text(
                            text = "As ${it.movieName}" ,
                            style = regularFont(12.sp , colorTextGray2) ,
                            maxLines = 1 ,
                            overflow = TextOverflow.Ellipsis
                        )

                    }


                }

            }
            
        }
        
    }

}