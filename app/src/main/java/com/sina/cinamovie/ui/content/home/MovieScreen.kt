package com.sina.cinamovie.ui.content.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sina.cinamovie.R
import com.sina.cinamovie.model.AppBarModel
import com.sina.cinamovie.model.GenreModel
import com.sina.cinamovie.model.ImageModel
import com.sina.cinamovie.model.VideoModel
import com.sina.cinamovie.ui.content.common.AppBar
import com.sina.cinamovie.ui.content.common.ListHeader
import com.sina.cinamovie.ui.content.list.*
import com.sina.cinamovie.ui.theme.*

@Composable
fun MovieScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBlack)
    ) {

        AppBar(
            model = AppBarModel(
                title = "Money Heist" ,
                subTitle = "2017 - 2022"
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 88.dp)
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
                            .data("https://m.media-amazon.com/images/M/MV5BNDJkYzY3MzMtMGFhYi00MmQ4LWJkNTgtZGNiZWZmMTMxNzdlXkEyXkFqcGdeQXVyMTEyMjM2NDc2._V1_.jpg")
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

                    val (genreParent , durationParent , rateParent) = createRefs()

                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .aspectRatio(1f / 1f)
                            .constrainAs(genreParent) {
                                top.linkTo(parent.top)
                            }
                            .padding(12.dp)
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = colorGray.copy(alpha = 0.75f)
                            ) ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        DetailMovie(
                            painter = painterResource(id = R.drawable.ic_camera_movie),
                            title = stringResource(id = R.string.str_genre),
                            desc = "Action"
                        )

                    }

                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .aspectRatio(1f / 1f)
                            .constrainAs(durationParent) {
                                top.linkTo(genreParent.bottom)
                            }
                            .padding(12.dp)
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = colorGray.copy(alpha = 0.75f)
                            ) ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        DetailMovie(
                            painter = painterResource(id = R.drawable.ic_time_five),
                            title = stringResource(R.string.str_duration),
                            desc = "1h 53m"
                        )

                    }

                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .aspectRatio(1f / 1f)
                            .constrainAs(rateParent) {
                                top.linkTo(durationParent.bottom)
                            }
                            .padding(12.dp)
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = colorGray.copy(alpha = 0.75f)
                            ) ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        DetailMovie(
                            painter = painterResource(id = R.drawable.ic_star),
                            title = stringResource(R.string.str_rating),
                            desc = "8.2/10"
                        )

                    }

                }

            }

            Spacer(modifier = Modifier.size(40.dp))

            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 24.dp) ,
                text = "Money Heist" ,
                style = mediumFont(24.sp)
            )

            Spacer(modifier = Modifier.size(12.dp))
            
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(horizontal = 24.dp) ,
                text = "An unusual group of robbers attempt to carry out the most perfect robbery in Spanish history - stealing 2.4 billion euros from the Royal Mint of Spain.An unusual group of robbers attempt to carry out the most perfect robbery in Spanish history - stealing 2.4 billion euros from the Royal Mint of Spain.An unusual group of robbers attempt to carry out the most perfect robbery in Spanish history - stealing 2.4 billion euros from the Royal Mint of Spain." ,
                style = regularFont(textColor = colorTextGray3) ,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.size(16.dp))

            GenreList(genreList = listOf(
                GenreModel(title = "Action") ,
                GenreModel(title = "Crime") ,
                GenreModel(title = "Drama")
            ))

            Spacer(modifier = Modifier.size(48.dp))

            ListHeader(title = stringResource(R.string.str_photos_and_videos) , true)

            Spacer(modifier = Modifier.size(24.dp))

            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp
            var heightSize by remember { mutableStateOf(IntSize.Zero) }

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

                    val (trailerParent , photosList , videosList) = createRefs()
                    val guideLine = createGuidelineFromTop(0.5f)

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
                                .data("https://m.media-amazon.com/images/M/MV5BNDJkYzY3MzMtMGFhYi00MmQ4LWJkNTgtZGNiZWZmMTMxNzdlXkEyXkFqcGdeQXVyMTEyMjM2NDc2._V1_.jpg")
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

                Column (
                    modifier = Modifier.height(with(LocalDensity.current) { heightSize.height.toDp() }) ,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    ImageList(
                        modifier = Modifier.height(with(LocalDensity.current) { heightSize.height.toDp() } / 2 - 8.dp) ,
                        itemSizeDp =  with(LocalDensity.current) { heightSize.height.toDp() } / 2 - 8.dp,
                        imageList = listOf(
                            ImageModel(original = "https://m.media-amazon.com/images/M/MV5BMTM0NjUxMDk5MF5BMl5BanBnXkFtZTcwNDMxNDY3Mw@@._V1_.jpg") ,
                            ImageModel(original = "https://m.media-amazon.com/images/M/MV5BMTk3NDE2Nzg3Nl5BMl5BanBnXkFtZTcwNTMxNDY3Mw@@._V1_.jpg") ,
                            ImageModel(original = "https://m.media-amazon.com/images/M/MV5BMTg0MDgwNjc5N15BMl5BanBnXkFtZTcwNjMxNDY3Mw@@._V1_.jpg") ,
                            ImageModel(original = "https://m.media-amazon.com/images/M/MV5BMTkzMTY0MjE5MV5BMl5BanBnXkFtZTcwODMxNDY3Mw@@._V1_.jpg") ,
                            ImageModel(original = "https://m.media-amazon.com/images/M/MV5BNTYxOTYyMzE3NV5BMl5BanBnXkFtZTcwOTMxNDY3Mw@@._V1_.jpg") ,
                            ImageModel(original = "https://m.media-amazon.com/images/M/MV5BMTgxMTU1MDkwOV5BMl5BanBnXkFtZTcwMDQxNDY3Mw@@._V1_.jpg")
                        )
                    )

                    VideoList(
                        modifier = Modifier.height(with(LocalDensity.current) { heightSize.height.toDp() } / 2 - 8.dp) ,
                        itemHeightDp = with(LocalDensity.current) { heightSize.height.toDp() } / 2 - 8.dp ,
                        itemWidthDp = with(LocalDensity.current) { heightSize.height.toDp() } ,
                        imageList = listOf(
                            VideoModel(preview = "https://m.media-amazon.com/images/M/MV5BMTM0NjUxMDk5MF5BMl5BanBnXkFtZTcwNDMxNDY3Mw@@._V1_.jpg") ,
                            VideoModel(preview = "https://m.media-amazon.com/images/M/MV5BMTk3NDE2Nzg3Nl5BMl5BanBnXkFtZTcwNTMxNDY3Mw@@._V1_.jpg") ,
                            VideoModel(preview = "https://m.media-amazon.com/images/M/MV5BMTg0MDgwNjc5N15BMl5BanBnXkFtZTcwNjMxNDY3Mw@@._V1_.jpg") ,
                            VideoModel(preview = "https://m.media-amazon.com/images/M/MV5BMTkzMTY0MjE5MV5BMl5BanBnXkFtZTcwODMxNDY3Mw@@._V1_.jpg") ,
                            VideoModel(preview = "https://m.media-amazon.com/images/M/MV5BNTYxOTYyMzE3NV5BMl5BanBnXkFtZTcwOTMxNDY3Mw@@._V1_.jpg") ,
                            VideoModel(preview = "https://m.media-amazon.com/images/M/MV5BMTgxMTU1MDkwOV5BMl5BanBnXkFtZTcwMDQxNDY3Mw@@._V1_.jpg")
                        )
                    )
                }

            }

        }

    }

}

@Composable
private fun DetailMovie(painter: Painter, title: String, desc: String) {

    Spacer(modifier = Modifier.size(16.dp))

    Image(
        modifier = Modifier
            .width(20.dp)
            .height(20.dp),
        painter = painter,
        contentDescription = "",
        colorFilter = ColorFilter.tint(colorWhite)
    )

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 4.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            verticalArrangement = Arrangement.Center ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = title , style = regularFont(14.sp , colorTextGray2) , maxLines = 1 , overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.size(2.dp))
            Text(text = desc , style = boldFont(16.sp , colorWhite) , maxLines = 1 , overflow = TextOverflow.Ellipsis)

        }

    }

}