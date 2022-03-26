package com.sina.cinamovie.ui.content.main.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sina.cinamovie.model.UserModel
import com.sina.cinamovie.ui.theme.colorGray
import com.sina.cinamovie.ui.theme.colorTextGray3
import com.sina.cinamovie.ui.theme.mediumFont
import com.sina.cinamovie.util.highResolutionImage
import timber.log.Timber

@Composable
fun CelebsScreen(userList: List<UserModel>) {

    LazyColumn(
        contentPadding = PaddingValues(16.dp) ,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(userList) { userModel ->

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(shape = RoundedCornerShape(16.dp), color = colorGray.copy(0.75f))
                    .padding(12.dp)
            ) {

                val (imageParent , detailParent) = createRefs()

                Timber.d("AvatarImage:: ${userModel.avatar.highResolutionImage()}")

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
                        .data(userModel.avatar.highResolutionImage())
                        .crossfade(true)
                        .build(),
                    contentDescription = "" ,
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .padding(start = 12.dp , top = 12.dp , bottom = 12.dp)
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
                            text = userModel.name ,
                            style = mediumFont(16.sp) ,
                            maxLines = 1 ,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.size(8.dp))

                        Text(
                            text = userModel.summary ,
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