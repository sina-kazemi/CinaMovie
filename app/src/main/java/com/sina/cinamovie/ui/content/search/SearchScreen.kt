package com.sina.cinamovie.ui.content.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.sina.cinamovie.ui.theme.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SearchScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.Transparent)
    ) {

        val pagerState = rememberPagerState()
        val pages = listOf(
            "Genre" , "Celebs" , "Titles" , "KeyWords"
        )

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                Box(
                    Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .height(4.dp)
                        .background(
                            shape = RoundedCornerShape(topStart = 16.dp , topEnd = 16.dp) ,
                            color = colorYellow
                        )
                ){

                }
            } ,
            backgroundColor = Color.Transparent ,
        ) {
            pages.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title ,
                            style = TextStyle(
                                fontSize = 14.sp ,
                                fontFamily = outfitFont ,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    },
                    selected = pagerState.currentPage == index,
                    selectedContentColor = colorWhite ,
                    unselectedContentColor = colorTextGray2 ,
                    onClick = {

                    }
                )
            }
        }

        HorizontalPager(
            count = pages.size,
            state = pagerState,
        ) { page ->
            // TODO: page content

            when (page) {
                1 -> {

                }
                2 -> {

                }
                3 -> {

                }
                4 -> {

                }
            }

        }

    }
}