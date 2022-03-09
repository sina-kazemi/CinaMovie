package com.sina.cinamovie.ui.content.home

import android.widget.TextView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sina.cinamovie.R
import com.sina.cinamovie.model.TrailerModel
import com.sina.cinamovie.model.UserModel
import com.sina.cinamovie.ui.theme.*

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = colorBlack)
    ) {

        Spacer(modifier = Modifier.size(32.dp))
        HomeAppbar(userModel = UserModel(firstName = "Sina"))
        Spacer(modifier = Modifier.size(24.dp))
        SearchComponent()
        Spacer(modifier = Modifier.size(32.dp))
        TrailerList(
                trailerList = listOf(
                    TrailerModel(),
                    TrailerModel(),
                    TrailerModel(),
                    TrailerModel()
                ))
        Spacer(modifier = Modifier.size(40.dp))
        ListHeader(title = stringResource(R.string.str_fan_favorites) , true)

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

@Composable
private fun ListHeader(title: String , showMore: Boolean = false) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp) ,
        verticalAlignment = Alignment.CenterVertically ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, style = mediumFont(16.sp))

        Row (
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = stringResource(R.string.str_more) ,
                style = regularFont(16.sp , colorYellow)
            )
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .offset(y = (1.5).dp),
                painter = painterResource(id = R.drawable.ic_chevron_right),
                contentDescription = stringResource(R.string.str_chevron_right),
                contentScale = ContentScale.FillHeight ,
                colorFilter = ColorFilter.tint(colorYellow)
            )
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TrailerList(trailerList: List<TrailerModel>) {

    val pagerState = rememberPagerState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            count = trailerList.size ,
            state = pagerState ,
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) { position ->

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
                        .background(color = colorGray, shape = RoundedCornerShape(16.dp)) ,
                    contentAlignment = Alignment.BottomCenter
                ) {

                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(shape = RoundedCornerShape(16.dp)) ,
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data(trailerList[position].preview)
                            .crossfade(true)
                            .build(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )

                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
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
                                text = trailerList[position].title ,
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
                                text = trailerList[position].duration ,
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