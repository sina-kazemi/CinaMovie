package com.sina.cinamovie.ui.content.main.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sina.cinamovie.R
import com.sina.cinamovie.model.MovieModel
import com.sina.cinamovie.ui.navigation.BottomNavItem
import com.sina.cinamovie.ui.theme.*
import com.sina.cinamovie.util.highResolutionImage
import java.text.DecimalFormat

@Composable
fun TitlesScreen(userList: List<MovieModel> , navController: NavHostController) {

    LazyColumn(
        contentPadding = PaddingValues(16.dp) ,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(userList) { movieModel ->

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("${BottomNavItem.Movie.screen_route}/${movieModel.titleId}")
                    }
                    .background(shape = RoundedCornerShape(16.dp), color = colorGray.copy(0.75f))
                    .padding(12.dp)
            ) {

                val (imageParent , detailParent) = createRefs()

                AsyncImage(
                    modifier = Modifier
                        .height(160.dp)
                        .aspectRatio(2f / 3f)
                        .background(
                            shape = RoundedCornerShape(8.dp),
                            color = colorGray.copy(alpha = 0.75f)
                        )
                        .clip(shape = RoundedCornerShape(8.dp))
                        .constrainAs(imageParent) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        },
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(movieModel.cover?.highResolutionImage())
                        .crossfade(true)
                        .build(),
                    contentDescription = "" ,
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
                        .constrainAs(detailParent) {
                            start.linkTo(imageParent.end)
                            end.linkTo(parent.end)
                            top.linkTo(imageParent.top)
                            bottom.linkTo(imageParent.bottom)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        }
                ) {

                    Column {

                        Text(
                            text = "${movieModel.title} ${movieModel.year}" ,
                            style = mediumFont(16.sp) ,
                            maxLines = 1 ,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.size(4.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically ,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Text(
                                modifier = Modifier.offset(y = (0.75).dp) ,
                                text = movieModel.rate.toString() ,
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

                            Spacer(modifier = Modifier.size(10.dp))

                            Text(
                                modifier = Modifier.offset(y = (0.75).dp) ,
                                text = kotlin.run {
                                    if (movieModel.voteCount != null && movieModel.voteCount!! < 1000) {
                                        "${movieModel.voteCount} Votes"
                                    }
                                    else {
                                        "${DecimalFormat("0,000").format(movieModel.voteCount)} Votes"
                                    }
                                } ,
                                style = lightFont(12.sp , colorTextGray2)
                            )

                        }

                        Spacer(modifier = Modifier.size(8.dp))

                        Text(
                            text = movieModel.summary.toString() ,
                            style = mediumFont(12.sp , colorTextGray3) ,
                            lineHeight = 20.sp ,
                            overflow = TextOverflow.Visible
                        )

                    }

                }

            }

        }

    }

}