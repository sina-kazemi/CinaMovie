package com.sina.cinamovie.ui.content.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.sina.cinamovie.R
import com.sina.cinamovie.model.AppBarModel
import com.sina.cinamovie.model.MenuModel
import com.sina.cinamovie.ui.content.common.AppBar
import com.sina.cinamovie.ui.theme.colorBlack
import com.sina.cinamovie.ui.theme.colorGray

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
        ) {

            ConstraintLayout (
                modifier = Modifier
//                    .fillMaxWidth()
                    .padding(bottom = 88.dp)
            ) {

                val (imageParent , detailParent) = createRefs()

                Box(
                    modifier = Modifier
                        .constrainAs(imageParent) {
                            start.linkTo(parent.start)
                            end.linkTo(detailParent.start)
                            top.linkTo(parent.top)
//                            width = Dimension.fillToConstraints
//                            height = Dimension.fillToConstraints
                        }
                        .aspectRatio(2f / 3f)
                        .background(shape = RoundedCornerShape(16.dp), color = Color.Cyan)
                ) {



                }

                ConstraintLayout (
                    modifier = Modifier
                        .constrainAs(detailParent) {
                            end.linkTo(parent.end)
                            top.linkTo(imageParent.top)
                            start.linkTo(imageParent.end)
                            bottom.linkTo(imageParent.bottom)
//                            width = Dimension.fillToConstraints
//                            height = Dimension.fillToConstraints
                        }
                        .aspectRatio(1f / 3f)
                ) {

                    val (genreParent , durationParent , rateParent) = createRefs()

                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .background(shape = RoundedCornerShape(16.dp), color = colorGray)
                            .aspectRatio(1f / 1f)
                            .constrainAs(genreParent) {
                                top.linkTo(parent.top)
                            }
                    ) {

                    }

                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .background(shape = RoundedCornerShape(16.dp), color = colorGray)
                            .aspectRatio(1f / 1f)
                            .constrainAs(durationParent) {
                                top.linkTo(genreParent.bottom)
                            }
                    ) {

                    }

                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .background(shape = RoundedCornerShape(16.dp), color = colorGray)
                            .aspectRatio(1f / 1f)
                            .constrainAs(rateParent) {
                                top.linkTo(durationParent.bottom)
                            }
                    ) {

                    }

                }

            }

        }

    }

}

@Preview
@Composable
fun preview() {
    MovieScreen()
}