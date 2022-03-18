package com.sina.cinamovie.ui.content.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sina.cinamovie.R
import com.sina.cinamovie.model.*
import com.sina.cinamovie.ui.content.common.AppBar
import com.sina.cinamovie.ui.content.common.ListHeader
import com.sina.cinamovie.ui.content.list.*
import com.sina.cinamovie.ui.theme.*

@Composable
fun MovieScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
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

                        DetailMovieHeader(
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

                        DetailMovieHeader(
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

                        DetailMovieHeader(
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
                    .padding(horizontal = 24.dp) ,
                text = "Money Heist" ,
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
                GenreModel(title = "Action") ,
                GenreModel(title = "Crime") ,
                GenreModel(title = "Drama")
            ))

            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp
            var heightSize by remember { mutableStateOf(IntSize.Zero) }

            Spacer(modifier = Modifier.size(48.dp))
            ListHeader(title = stringResource(R.string.str_photos_and_videos) , true)
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
            ListHeader(title = stringResource(R.string.str_casts) , true)
            Spacer(modifier = Modifier.size(16.dp))
            Column {

                listOf(
                    CastModel(realName = "Brad Pit1" , movieName = "Joe Hill" , image = "https://m.media-amazon.com/images/M/MV5BMTc0MDMyMzI2OF5BMl5BanBnXkFtZTcwMzM2OTk1MQ@@._V1_.jpg"),
                    CastModel(realName = "Brad Pit2" , movieName = "Joe Hill" , image = "https://m.media-amazon.com/images/M/MV5BMTc0MDMyMzI2OF5BMl5BanBnXkFtZTcwMzM2OTk1MQ@@._V1_.jpg"),
                    CastModel(realName = "Brad Pit3" , movieName = "Joe Hill" , image = "https://m.media-amazon.com/images/M/MV5BMTc0MDMyMzI2OF5BMl5BanBnXkFtZTcwMzM2OTk1MQ@@._V1_.jpg"),
                    CastModel(realName = "Brad Pit4" , movieName = "Joe Hill" , image = "https://m.media-amazon.com/images/M/MV5BMTc0MDMyMzI2OF5BMl5BanBnXkFtZTcwMzM2OTk1MQ@@._V1_.jpg"),
                    CastModel(realName = "Brad Pit5" , movieName = "Joe Hill" , image = "https://m.media-amazon.com/images/M/MV5BMTc0MDMyMzI2OF5BMl5BanBnXkFtZTcwMzM2OTk1MQ@@._V1_.jpg"),
                    CastModel(realName = "Brad Pit6" , movieName = "Joe Hill" , image = "https://m.media-amazon.com/images/M/MV5BMTc0MDMyMzI2OF5BMl5BanBnXkFtZTcwMzM2OTk1MQ@@._V1_.jpg"),
                    CastModel(realName = "Brad Pit7" , movieName = "Joe Hill" , image = "https://m.media-amazon.com/images/M/MV5BMTc0MDMyMzI2OF5BMl5BanBnXkFtZTcwMzM2OTk1MQ@@._V1_.jpg"),
                    CastModel(realName = "Brad Pit8" , movieName = "Joe Hill" , image = "https://m.media-amazon.com/images/M/MV5BMTc0MDMyMzI2OF5BMl5BanBnXkFtZTcwMzM2OTk1MQ@@._V1_.jpg"),
                    CastModel(realName = "Brad Pit9" , movieName = "Joe Hill" , image = "https://m.media-amazon.com/images/M/MV5BMTc0MDMyMzI2OF5BMl5BanBnXkFtZTcwMzM2OTk1MQ@@._V1_.jpg"),
                    CastModel(realName = "Brad Pit9" , movieName = "Joe Hill" , image = "https://m.media-amazon.com/images/M/MV5BMTc0MDMyMzI2OF5BMl5BanBnXkFtZTcwMzM2OTk1MQ@@._V1_.jpg"),
                    CastModel(realName = "Brad Pit9" , movieName = "Joe Hill" , image = "https://m.media-amazon.com/images/M/MV5BMTc0MDMyMzI2OF5BMl5BanBnXkFtZTcwMzM2OTk1MQ@@._V1_.jpg")
                ).windowed(3 , 3, true).forEach { subList ->

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)) {

                        subList.forEach {

                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp) ,
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
                                    contentDescription = "" ,
                                    contentScale = ContentScale.Crop
                                )

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

                        repeat(3-subList.size) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp) ,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){}
                        }

                    }

                }

            }

            Spacer(modifier = Modifier.size(48.dp))
            ListHeader(title = stringResource(R.string.str_more_like_this) , true)
            Spacer(modifier = Modifier.size(24.dp))
            MovieList(movieList = listOf(
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
                ) ,
            ))
            Spacer(modifier = Modifier.size(24.dp))

        }

    }

}

@Composable
private fun DetailMovieHeader(painter: Painter, title: String, desc: String) {

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