package com.sina.cinamovie.ui.home

import android.widget.Space
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sina.cinamovie.R
import com.sina.cinamovie.model.UserModel
import com.sina.cinamovie.ui.theme.*

@Composable
fun AllContentHome() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = colorBlack)
            .statusBarsPadding()
    ) {

        val systemUiController = rememberSystemUiController()

        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )

        Spacer(modifier = Modifier.size(32.dp))
        HomeAppbar(userModel = UserModel(firstName = "Sina"))
        Spacer(modifier = Modifier.size(24.dp))
        SearchComponent()
        Spacer(modifier = Modifier.size(48.dp))
        ListHeader(title = "Favorites" , true)

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
            Text(text = stringResource(R.string.str_more) , style = regularFont(16.sp , colorYellow))
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .offset(y = 1.dp),
                painter = painterResource(id = R.drawable.ic_chevron_right),
                contentDescription = stringResource(R.string.str_chevron_right),
                contentScale = ContentScale.FillHeight ,
                colorFilter = ColorFilter.tint(colorYellow)
            )
        }
    }

}