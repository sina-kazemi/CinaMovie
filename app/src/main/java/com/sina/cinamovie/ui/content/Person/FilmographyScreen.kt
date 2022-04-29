package com.sina.cinamovie.ui.content.Person

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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sina.cinamovie.R
import com.sina.cinamovie.model.MovieModel
import com.sina.cinamovie.ui.navigation.BottomNavItem
import com.sina.cinamovie.ui.theme.*

@Composable
fun FilmographyScreen(movieList: List<MovieModel> , navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp) ,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Spacer(modifier = Modifier.size(4.dp))

        movieList.forEach { movieModel ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        shape = RoundedCornerShape(16.dp),
                        color = colorGray.copy(alpha = 0.75f)
                    )
                    .clickable {
                        navController.navigate("${BottomNavItem.Movie.screen_route}/${movieModel.titleId}")
                    },
                horizontalArrangement = Arrangement.SpaceBetween ,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column (
                    modifier = Modifier.padding(16.dp) ,
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        text = "${movieModel.title} (${movieModel.year})" ,
                        style = mediumFont() ,
                        maxLines = 1 ,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (movieModel.subTitle != null) {

                        Spacer(modifier = Modifier.size(2.dp))

                        Text(
                            text = movieModel.subTitle!! ,
                            style = regularFont(12.sp , colorTextGray2) ,
                            maxLines = 1 ,
                            overflow = TextOverflow.Ellipsis
                        )

                    }

                }

                Image(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(20.dp, 20.dp) ,
                    painter = painterResource(id = R.drawable.ic_chevron_right) ,
                    contentDescription = "" ,
                    colorFilter = ColorFilter.tint(colorWhite)
                )

            }

        }

        Spacer(modifier = Modifier.size(4.dp))

    }

}