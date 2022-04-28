package com.sina.cinamovie.ui.content.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.sina.cinamovie.R
import com.sina.cinamovie.model.AppBarModel
import com.sina.cinamovie.model.MenuModel
import com.sina.cinamovie.ui.navigation.BottomNavItem
import com.sina.cinamovie.ui.navigation.MainBottomNavItem
import com.sina.cinamovie.ui.theme.*
import timber.log.Timber

@Composable
fun AppBar(model: AppBarModel , navController: NavHostController) {

    Row(
        modifier = Modifier.fillMaxWidth() ,
        verticalAlignment = Alignment.CenterVertically ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier.wrapContentWidth() ,
            verticalAlignment = Alignment.CenterVertically ,
        ) {

            if (model.backIcon == null) {
                Timber.d("BackIconIsNull")

                model.backIcon = MenuModel(painter = painterResource(id = R.drawable.ic_chevron_left)) {
                    navController.navigateUp()
                }
            }

            MenuIcon(menuModel = model.backIcon!!)

            Column(
                modifier = Modifier
                    .wrapContentWidth() ,
                horizontalAlignment = Alignment.Start
            ) {

                Text(text = model.title, style = mediumFont(16.sp))
                if (model.subTitle.trim() != "") {
                    Text(text = model.subTitle, style = lightFont(12.sp , colorTextGray))
                }

            }

        }

        Row(
            modifier = Modifier.wrapContentWidth() ,
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.End
        ) {
            model.menuList.forEachIndexed { index, menuModel ->
                if (index == model.menuList.size-1) {
                    MenuIcon(menuModel = menuModel)
                }
                else {
                    MenuIcon(menuModel = menuModel , offset = (12).dp)
                }
            }
        }

    }

}

@Composable
fun ListHeader(title: String , showMore: Boolean = false , showPlaceHolder: Boolean = false) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .placeholder(
                visible = showPlaceHolder,
                color = colorGray,
                shape = RoundedCornerShape(16.dp),
                highlight = PlaceholderHighlight.fade(
                    highlightColor = colorPlaceHolder
                )
            ),
        verticalAlignment = Alignment.CenterVertically ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, style = mediumFont(16.sp))

        if (showMore) {

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

}

@Composable
private fun MenuIcon(menuModel: MenuModel , offset: Dp = 0.dp) {
    Image(
        modifier = Modifier
            .width(56.dp)
            .height(56.dp)
            .offset(x = offset)
            .clickable(
                onClick = {
                    Timber.d("CLICKED:::!!!!")
                    menuModel.onClick.invoke()
                }
            )
            .padding(16.dp),
        painter = menuModel.painter!! ,
        contentDescription = "" ,
        colorFilter = ColorFilter.tint(menuModel.iconColor)
    )
}
