package com.sina.cinamovie.ui.content.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.sina.cinamovie.R
import com.sina.cinamovie.model.AppBarModel
import com.sina.cinamovie.model.MenuModel
import com.sina.cinamovie.ui.content.common.AppBar
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
                title = "Black Panther" ,
                subTitle = "2022"
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

                        DetailMovieScreen(
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

                        DetailMovieScreen(
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

                        DetailMovieScreen(
                            painter = painterResource(id = R.drawable.ic_star),
                            title = stringResource(R.string.str_rating),
                            desc = "8.2/10"
                        )

                    }

                }

            }

        }

    }

}

@Composable
fun DetailMovieScreen(painter: Painter , title: String , desc: String) {

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
        modifier = Modifier.fillMaxHeight().padding(bottom = 4.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            verticalArrangement = Arrangement.Center ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = title , style = regularFont(14.sp , colorTextGray2))
            Spacer(modifier = Modifier.size(2.dp))
            Text(text = desc , style = boldFont(16.sp , colorWhite))

        }

    }


}