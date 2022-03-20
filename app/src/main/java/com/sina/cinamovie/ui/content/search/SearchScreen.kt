package com.sina.cinamovie.ui.content.search

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.sina.cinamovie.R
import com.sina.cinamovie.ui.theme.*
import com.sina.cinamovie.util.toDp
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SearchScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.Transparent)
    ) {

        val pages = listOf(
            "Genre" , "Celebs" , "Titles" , "KeyWords"
        )
        val density = LocalDensity.current
        val pagerState = rememberPagerState()
        val scope = rememberCoroutineScope()
        val tabWidths = remember {
            val tabWidthStateList = mutableStateListOf<Dp>()
            repeat(pages.size) {
                tabWidthStateList.add(0.dp)
            }
            tabWidthStateList
        }

        Spacer(modifier = Modifier.size(24.dp))

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 24.dp)
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

        Spacer(modifier = Modifier.size(16.dp))

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->

                Timber.d("IndicatorPos:: ${tabWidths[pagerState.currentPage]}")

                val currentTabWidth by animateDpAsState(
                    targetValue = tabWidths[pagerState.currentPage],
                    animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
                )
                val indicatorOffset by animateDpAsState(
                    targetValue = (
                            (tabPositions[pagerState.currentPage].left +
                            tabPositions[pagerState.currentPage].right -
                            tabWidths[pagerState.currentPage]) / 2),
                    animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
                )

                Box(

                    Modifier
                        .wrapContentSize(Alignment.BottomStart)
                        .offset(x = indicatorOffset)
                        .width(currentTabWidth)
                        .height(4.dp)
                        .background(
                            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
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
                            ) ,
                            onTextLayout = { textLayoutResult ->

                                tabWidths[index] =
                                    with(density) { textLayoutResult.size.width.toDp() }

                            }
                        )
                    },
                    selected = pagerState.currentPage == index,
                    selectedContentColor = colorWhite ,
                    unselectedContentColor = colorTextGray2 ,
                    onClick = {
                        scope.launch { pagerState.animateScrollToPage(index) }
                    }
                )
            }
        }

        Divider(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(colorDivider))

        HorizontalPager(
            count = pages.size,
            state = pagerState,
        ) { page ->
            // TODO: page content
            Timber.d("PAGE_SWIPE:: $page")

            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = page.toString(), style = boldFont(26.sp))
            }

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


fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "customTabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
        .height(4.dp)
        .background(
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            color = colorYellow
        )
}