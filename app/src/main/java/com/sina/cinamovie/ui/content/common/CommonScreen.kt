package com.sina.cinamovie.ui.content.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sina.cinamovie.R
import com.sina.cinamovie.model.AppBarModel
import com.sina.cinamovie.model.MenuModel
import com.sina.cinamovie.ui.theme.*

@Composable
fun AppBar(model: AppBarModel) {

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
                model.backIcon = MenuModel(painter = painterResource(id = R.drawable.ic_left_arrow)) {

                }
            }

            MenuIcon(menuModel = model.backIcon!!)

            Column(
                modifier = Modifier
                    .wrapContentWidth() ,
                horizontalAlignment = Alignment.Start
            ) {

                Text(text = model.title, style = mediumFont(14.sp))
                Spacer(modifier = Modifier.size(2.dp))
                Text(text = model.subTitle, style = lightFont(12.sp , colorTextGray))

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
private fun MenuIcon(menuModel: MenuModel , offset: Dp = 0.dp) {
    Image(
        modifier = Modifier
            .width(56.dp)
            .height(56.dp)
            .offset(x = offset)
            .clickable(
                onClick = {
                    menuModel.onClick
                }
            )
            .padding(16.dp),
        painter = menuModel.painter!! ,
        contentDescription = "" ,
        colorFilter = ColorFilter.tint(menuModel.iconColor)
    )
}