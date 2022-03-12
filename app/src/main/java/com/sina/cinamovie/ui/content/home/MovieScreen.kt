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
                title = "BlackPanther" ,
                subTitle = "2022"
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            ConstraintLayout {

                val (imageParent , detailParent) = createRefs()

                ConstraintLayout (
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .fillMaxHeight(0.4f)
                        .constrainAs(detailParent) {
                            start.linkTo(imageParent.end)
                            end.linkTo(parent.end)
                            top.linkTo(imageParent.top)
                        }
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

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .aspectRatio(2f / 3f)
                        .background(shape = RoundedCornerShape(16.dp), color = Color.Cyan)
                        .constrainAs(imageParent) {
                            start.linkTo(parent.start)
                            end.linkTo(detailParent.start)
                            top.linkTo(detailParent.top)
                            bottom.linkTo(detailParent.bottom)
                        }
                ) {



                }

            }

        }

    }

}