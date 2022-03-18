package com.sina.cinamovie.ui.content.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sina.cinamovie.R
import com.sina.cinamovie.model.MovieModel
import com.sina.cinamovie.model.NewsModel
import com.sina.cinamovie.model.TrailerModel
import com.sina.cinamovie.model.UserModel
import com.sina.cinamovie.ui.content.common.ListHeader
import com.sina.cinamovie.ui.content.list.IMDbOriginalsList
import com.sina.cinamovie.ui.content.list.MovieList
import com.sina.cinamovie.ui.content.list.TrailerList
import com.sina.cinamovie.ui.theme.*

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.Transparent)
    ) {

        Spacer(modifier = Modifier.size(32.dp))

        HomeAppbar(userModel = UserModel(firstName = "Sina"))

        Spacer(modifier = Modifier.size(24.dp))

        SearchComponent()

        Spacer(modifier = Modifier.size(32.dp))

        TrailerList(trailerList = listOf(
            TrailerModel(
                preview = "https://m.media-amazon.com/images/M/MV5BMjZlOWNjM2EtYTcwZS00YjAwLTk2ZGMtMDE5MTAwMmY5YTJhXkEyXkFqcGdeQXVyNjY1MTg4Mzc@._CR66,511,4247,2389.jpg" ,
                title = "How Kristen Stewart Nailed Princess Di's Accent" ,
                duration = "4:27"
            ) ,
            TrailerModel(
                preview = "https://m.media-amazon.com/images/M/MV5BOTJiYjdiZmUtYmM5Ni00MjhmLWI2ODctZjA3Yjc0M2U4MzhmXkEyXkFqcGdeQWpnYW1i._V1_QL40_.jpg" ,
                title = "Ana de Armas and Ben Affleck in 'Deep Water'" ,
                duration = "3:18"
            ) ,
            TrailerModel(
                preview = "https://m.media-amazon.com/images/M/MV5BMjZlOWNjM2EtYTcwZS00YjAwLTk2ZGMtMDE5MTAwMmY5YTJhXkEyXkFqcGdeQXVyNjY1MTg4Mzc@._CR66,511,4247,2389.jpg" ,
                title = "How Kristen Stewart Nailed Princess Di's Accent" ,
                duration = "4:27"
            ) ,
            TrailerModel(
                preview = "https://m.media-amazon.com/images/M/MV5BOTJiYjdiZmUtYmM5Ni00MjhmLWI2ODctZjA3Yjc0M2U4MzhmXkEyXkFqcGdeQWpnYW1i._V1_QL40_.jpg" ,
                title = "Ana de Armas and Ben Affleck in 'Deep Water'" ,
                duration = "3:18"
            ) ,
            TrailerModel(
                preview = "https://m.media-amazon.com/images/M/MV5BMjZlOWNjM2EtYTcwZS00YjAwLTk2ZGMtMDE5MTAwMmY5YTJhXkEyXkFqcGdeQXVyNjY1MTg4Mzc@._CR66,511,4247,2389.jpg" ,
                title = "How Kristen Stewart Nailed Princess Di's Accent" ,
                duration = "4:27"
            ) ,
            TrailerModel(
                preview = "https://m.media-amazon.com/images/M/MV5BOTJiYjdiZmUtYmM5Ni00MjhmLWI2ODctZjA3Yjc0M2U4MzhmXkEyXkFqcGdeQWpnYW1i._V1_QL40_.jpg" ,
                title = "Ana de Armas and Ben Affleck in 'Deep Water'" ,
                duration = "3:18"
            )
                ))

        Spacer(modifier = Modifier.size(40.dp))

        ListHeader(title = stringResource(R.string.str_fan_favorites) , true)
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

        Spacer(modifier = Modifier.size(48.dp))

        ListHeader(title = stringResource(R.string.str_coming_soon) , true)
        Spacer(modifier = Modifier.size(24.dp))
        MovieList(movieList = listOf(
            MovieModel(
                cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg" ,
                title = "The Batman"
            ) ,
            MovieModel(
                cover = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                title = "Spider-Man: No way home"
            ) ,
            MovieModel(
                cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg" ,
                title = "The Batman"
            ) ,
            MovieModel(
                cover = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                title = "Spider-Man: No way home"
            ) ,
            MovieModel(
                cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg" ,
                title = "The Batman"
            ) ,
            MovieModel(
                cover = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                title = "Spider-Man: No way home"
            ) ,
            MovieModel(
                cover = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg" ,
                title = "The Batman"
            ) ,
            MovieModel(
                cover = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                title = "Spider-Man: No way home"
            ) ,
        ))

        Spacer(modifier = Modifier.size(48.dp))

        IMDbOriginalsList(trailerList = listOf(
            TrailerModel(
                preview = "https://m.media-amazon.com/images/M/MV5BMjZlOWNjM2EtYTcwZS00YjAwLTk2ZGMtMDE5MTAwMmY5YTJhXkEyXkFqcGdeQXVyNjY1MTg4Mzc@._CR66,511,4247,2389.jpg" ,
                title = "How Kristen Stewart Nailed Princess Di's Accent" ,
                duration = "4:27"
            ) ,
            TrailerModel(
                preview = "https://m.media-amazon.com/images/M/MV5BOTJiYjdiZmUtYmM5Ni00MjhmLWI2ODctZjA3Yjc0M2U4MzhmXkEyXkFqcGdeQWpnYW1i._V1_QL40_.jpg" ,
                title = "Ana de Armas and Ben Affleck in 'Deep Water'" ,
                duration = "3:18"
            ) ,
            TrailerModel(
                preview = "https://m.media-amazon.com/images/M/MV5BMjZlOWNjM2EtYTcwZS00YjAwLTk2ZGMtMDE5MTAwMmY5YTJhXkEyXkFqcGdeQXVyNjY1MTg4Mzc@._CR66,511,4247,2389.jpg" ,
                title = "How Kristen Stewart Nailed Princess Di's Accent" ,
                duration = "4:27"
            ) ,
            TrailerModel(
                preview = "https://m.media-amazon.com/images/M/MV5BOTJiYjdiZmUtYmM5Ni00MjhmLWI2ODctZjA3Yjc0M2U4MzhmXkEyXkFqcGdeQWpnYW1i._V1_QL40_.jpg" ,
                title = "Ana de Armas and Ben Affleck in 'Deep Water'" ,
                duration = "3:18"
            ) ,
            TrailerModel(
                preview = "https://m.media-amazon.com/images/M/MV5BMjZlOWNjM2EtYTcwZS00YjAwLTk2ZGMtMDE5MTAwMmY5YTJhXkEyXkFqcGdeQXVyNjY1MTg4Mzc@._CR66,511,4247,2389.jpg" ,
                title = "How Kristen Stewart Nailed Princess Di's Accent" ,
                duration = "4:27"
            ) ,
            TrailerModel(
                preview = "https://m.media-amazon.com/images/M/MV5BOTJiYjdiZmUtYmM5Ni00MjhmLWI2ODctZjA3Yjc0M2U4MzhmXkEyXkFqcGdeQWpnYW1i._V1_QL40_.jpg" ,
                title = "Ana de Armas and Ben Affleck in 'Deep Water'" ,
                duration = "3:18"
            )
        ))

        Spacer(modifier = Modifier.size(48.dp))

        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp

        ListHeader(title = stringResource(R.string.str_news) , true)
        Spacer(modifier = Modifier.size(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()) ,
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Spacer(modifier = Modifier.size(0.dp))

            listOf(
                NewsModel(
                    date = "14 Feb" ,
                    image = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                    title = "This is a news for test news This is a news for test news This is a news for test news This is a news for test news" ,
                    source = "Indipendent"
                ) ,
                NewsModel(
                    date = "14 Feb" ,
                    image = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                    title = "This is a news for test news" ,
                    source = "Indipendent"
                ) ,
                NewsModel(
                    date = "14 Feb" ,
                    image = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                    title = "This is a news for test news" ,
                    source = "Indipendent"
                ) ,
                NewsModel(
                    date = "14 Feb" ,
                    image = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                    title = "This is a news for test news" ,
                    source = "Indipendent"
                ) ,
                NewsModel(
                    date = "14 Feb" ,
                    image = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                    title = "This is a news for test news" ,
                    source = "Indipendent"
                ) ,
                NewsModel(
                    date = "14 Feb" ,
                    image = "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg" ,
                    title = "This is a news for test news" ,
                    source = "Indipendent"
                )
            ).windowed(2 , 2 , true).forEach { subList ->

                Column(
                    modifier = Modifier
                        .width(screenWidth - 32.dp) ,
                    verticalArrangement = Arrangement.spacedBy(16.dp) ,
                ) {

                    subList.forEach {

                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxWidth() ,
                        ) {

                            val (imageParent , detailParent) = createRefs()

                            AsyncImage(
                                modifier = Modifier
                                    .aspectRatio(1f / 1f)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(
                                        shape = RoundedCornerShape(16.dp),
                                        color = colorGray.copy(alpha = 0.75f)
                                    )
                                    .constrainAs(imageParent) {
                                        top.linkTo(detailParent.top)
                                        bottom.linkTo(detailParent.bottom)
//                                        width = Dimension.fillToConstraints
                                        height = Dimension.fillToConstraints
                                    },
                                model = ImageRequest
                                    .Builder(LocalContext.current)
                                    .data(it.image)
                                    .crossfade(true)
                                    .build(),
                                contentScale = ContentScale.Crop ,
                                contentDescription = ""
                            )

                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .constrainAs(detailParent) {
                                        start.linkTo(imageParent.end)
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                        end.linkTo(parent.end)
                                        width = Dimension.fillToConstraints
//                                        height = Dimension.fillToConstraints
                                    },
                                horizontalAlignment = Alignment.Start ,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Spacer(modifier = Modifier.size(6.dp))

                                Text(
                                    text = it.source ,
                                    style = regularFont(12.sp , colorTextGray2) ,
                                    maxLines = 1 ,
                                    overflow = TextOverflow.Ellipsis ,
                                    textAlign = TextAlign.Start
                                )

                                Spacer(modifier = Modifier.size(4.dp))

                                Column {

//                                    var lines by remember { mutableStateOf(0) }
                                    val lineHeight = 14.sp*4/3

                                    Text(
                                        modifier = Modifier
                                            .sizeIn(minHeight = with(LocalDensity.current) {
                                                (lineHeight*2).toDp()
                                            }),
                                        text = it.title ,
                                        style = regularFont() ,
                                        maxLines = 2 ,
                                        overflow = TextOverflow.Ellipsis ,
                                        textAlign = TextAlign.Start,
                                        lineHeight = lineHeight
                                    )

//                                    repeat(2 - lines.coerceAtMost(2)) {
//                                        Text(text = "", style = regularFont())
//                                    }

                                }

                                Spacer(modifier = Modifier.size(8.dp))

                                Text(
                                    text = it.date ,
                                    style = regularFont(12.sp , colorTextGray2) ,
                                    maxLines = 1 ,
                                    overflow = TextOverflow.Ellipsis ,
                                    textAlign = TextAlign.Start
                                )

                                Spacer(modifier = Modifier.size(6.dp))

                            }

                        }

                    }

                }

            }

            Spacer(modifier = Modifier.size(0.dp))

        }

        Spacer(modifier = Modifier.size(48.dp))
    }
}

@Composable
private fun HomeAppbar(
    userModel: UserModel
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 32.dp) ,
        verticalAlignment = Alignment.CenterVertically ,
        horizontalArrangement = Arrangement.SpaceBetween
        ) {

        Column (horizontalAlignment = Alignment.Start){

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(R.string.str_hello) , style = boldFont(20.sp))
                Spacer(modifier = Modifier.size(2.dp))
                Text(text = userModel.firstName , style = extraLightFont(20.sp))
            }
            Spacer(modifier = Modifier.size(2.dp))
            Text(text = stringResource(R.string.str_main_appbar_desc) , style = extraLightFont(12.sp))

        }

        Box(
            modifier = Modifier
                .width(56.dp)
                .height(56.dp)
                .background(
                    shape = RoundedCornerShape(16.dp),
                    color = colorGray.copy(alpha = 0.75f)
                )
        ) {
            if (userModel.avatarUrl.trim() == "") {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp) ,
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = stringResource(R.string.str_user),
                    colorFilter = ColorFilter.tint(colorWhite)
                )
            }
            else {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(userModel.avatarUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.str_avatar),
                    contentScale = ContentScale.Crop
                )
            }
        }

    }

}

@Composable
private fun SearchComponent() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 32.dp)
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

}
