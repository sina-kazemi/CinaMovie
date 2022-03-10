package com.sina.cinamovie.ui.content.list

import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.sina.cinamovie.R
import com.sina.cinamovie.model.MovieModel
import com.sina.cinamovie.model.TrailerModel
import com.sina.cinamovie.ui.theme.*
import timber.log.Timber

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TrailerList(trailerList: List<TrailerModel>) {

    val pagerState = rememberPagerState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            count = trailerList.size ,
            state = pagerState ,
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) { position ->

            TrailerRow(model = trailerList[position])

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
fun TrailerRow(model: TrailerModel , parentWidth: Dp = (-1).dp , horizontalPadding: Dp = 8.dp) {

    ConstraintLayout(
        modifier = run {
            if (parentWidth < 0.dp) {
                Modifier.fillMaxWidth()
            }
            else {
                Modifier.width(parentWidth)
            }
        }
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
                .padding(horizontal = horizontalPadding)
                .background(color = colorGray, shape = RoundedCornerShape(16.dp)) ,
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
                            color = colorTextGray
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
                        text = model.title ,
                        style = TextStyle(
                            color = colorWhite ,
                            fontSize = 14.sp ,
                            fontFamily = outfitFont ,
                            fontWeight = FontWeight.Normal,
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
                        text = model.duration ,
                        style = TextStyle(
                            color = colorWhite ,
                            fontSize = 12.sp ,
                            fontFamily = outfitFont ,
                            fontWeight = FontWeight.Bold,
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
fun MovieList(movieList: List<MovieModel>) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp) ,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {

        items(movieList.size) { position ->

            ConstraintLayout {

                val (imageParent , title , rateParent , spacer1 , spacer2) = createRefs()

                Box(
                    modifier = Modifier
                        .width(96.dp)
                        .aspectRatio(2f / 3f)
                        .background(shape = RoundedCornerShape(16.dp), color = colorGray)
                        .constrainAs(imageParent) {

                        }
                ) {

                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(shape = RoundedCornerShape(16.dp)),
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data(movieList[position].cover)
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
                        .constrainAs(title) {
                            top.linkTo(spacer1.bottom)
                            start.linkTo(imageParent.start)
                            end.linkTo(imageParent.end)
                            width = Dimension.fillToConstraints
                        },
                    text = movieList[position].title ,
                    style = regularFont(12.sp) ,
                    maxLines = 1 ,
                    overflow = TextOverflow.Ellipsis ,
                    textAlign = TextAlign.Center
                )

                if (movieList[position].rate != null) {

                    Spacer(
                        modifier = Modifier
                            .size(4.dp)
                            .constrainAs(spacer2) {
                                top.linkTo(title.bottom)
                            }
                    )

                    Row(
                        modifier = Modifier
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
                            text = movieList[position].rate.toString() ,
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

    }


}

@Composable
fun IMDbOriginalsList(trailerList: List<TrailerModel>) {

    var titleParentSize by remember {
        mutableStateOf(IntSize.Zero)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        Box(
            modifier = Modifier
                .onSizeChanged { titleParentSize = it }
                .align(Alignment.CenterStart)
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

        val widthInDp = with(LocalDensity.current) { titleParentSize.width.toDp() }
        var offset by remember { mutableStateOf(0f) }

        LazyRow(
            modifier = Modifier.scrollable(
                orientation = Orientation.Horizontal ,
                state = ScrollableState {
                    offset += it
                    Timber.d("ScrollableState:: $offset")
                    it
                }
            ) ,
            horizontalArrangement = Arrangement.spacedBy(16.dp) ,
            contentPadding = PaddingValues(start = widthInDp , end = 16.dp)
        ) {

            items(trailerList.size) { position ->

                TrailerRow(model = trailerList[position] , parentWidth = 320.dp, horizontalPadding = 0.dp)

            }

        }

    }

}