package com.sina.cinamovie.ui.content.movielist

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sina.cinamovie.model.AppBarModel
import com.sina.cinamovie.model.MovieModel
import com.sina.cinamovie.ui.content.common.AppBar
import com.sina.cinamovie.ui.content.list.MovieItem
import com.sina.cinamovie.ui.navigation.BottomNavItem
import com.sina.cinamovie.ui.theme.*
import com.sina.cinamovie.R
import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.res.GenresRes
import com.sina.cinamovie.data.res.SearchTitlesRes
import com.sina.cinamovie.vm.SearchViewModel
import timber.log.Timber
import java.lang.Exception

@Composable
fun MovieListScreen(genres: String , navController: NavHostController , searchViewModel: SearchViewModel) {

    LaunchedEffect(true) {
        searchViewModel.advancedSearchTitle(genres = genres)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val advancedSearchFlowLifecycleAware = remember(searchViewModel.advancedSearchTitleUiState, lifecycleOwner) {
        searchViewModel.advancedSearchTitleUiState.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val advancedSearchRes: Result<ApiResponse<List<SearchTitlesRes>>> by advancedSearchFlowLifecycleAware.collectAsState(initial = Result.loading())

    val showPlaceHolder by remember {
        derivedStateOf { advancedSearchRes.status == Result.Status.LOADING }
    }

    Timber.d("advancedSearchResStatus:: ${advancedSearchRes.status}")
    advancedSearchRes.data?.data?.let {
        Timber.d("advancedSearchRes:: $it")
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        AppBar(model = AppBarModel(title = genres), navController = navController)

        var tempList = listOf<SearchTitlesRes>()
        if (showPlaceHolder) {
            tempList = listOf(
                SearchTitlesRes("" , "" , listOf() , "" , "9.9" , "" , "" , "" , listOf() , "" , "" , "" , ""),
                SearchTitlesRes("" , "" , listOf() , "" , "9.9" , "" , "" , "" , listOf() , "" , "" , "" , ""),
                SearchTitlesRes("" , "" , listOf() , "" , "9.9" , "" , "" , "" , listOf() , "" , "" , "" , ""),
                SearchTitlesRes("" , "" , listOf() , "" , "9.9" , "" , "" , "" , listOf() , "" , "" , "" , ""),
                SearchTitlesRes("" , "" , listOf() , "" , "9.9" , "" , "" , "" , listOf() , "" , "" , "" , ""),
                SearchTitlesRes("" , "" , listOf() , "" , "9.9" , "" , "" , "" , listOf() , "" , "" , "" , ""),
                SearchTitlesRes("" , "" , listOf() , "" , "9.9" , "" , "" , "" , listOf() , "" , "" , "" , ""),
                SearchTitlesRes("" , "" , listOf() , "" , "9.9" , "" , "" , "" , listOf() , "" , "" , "" , "")
            )
        }
        else {
            advancedSearchRes.data?.data?.let {
                tempList = it
            }
        }

        GridList(items = tempList, navController = navController , showPlaceHolder = showPlaceHolder)

    }

}

@Composable
fun GridList(items: List<SearchTitlesRes> , navController: NavHostController , showPlaceHolder: Boolean) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) ,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Spacer(modifier = Modifier.size(0.dp))

        val movieList: List<MovieModel> = items.map {
            MovieModel(title = it.title , titleId = it.titleId , cover = it.cover , rate = kotlin.run { try { it.imdbRating?.toFloat() } catch (e: Exception) { 0f } } )
        }

        movieList.windowed(3 , 3 , true).forEach { subList ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp) ,
                horizontalArrangement = Arrangement.spacedBy(16.dp) ,
            ) {

                subList.forEach { item ->

                    MovieItem(item = item, navController = navController , isGridList = true , modifier = Modifier.weight(1f) , showPlaceHolder = showPlaceHolder)

//                    ConstraintLayout(
//                        modifier = Modifier
//                            .weight(1f)
//                            .clickable { navController.navigate(BottomNavItem.Movie.screen_route + "/" + "123") }
//                    ) {
//
//                        val (imageParent , title , subTitle , rateParent , spacer1 , spacer2) = createRefs()
//
//                        Box(
//                            modifier = Modifier
//                                .aspectRatio(2f / 3f)
//                                .background(shape = RoundedCornerShape(16.dp), color = colorGray)
//                                .constrainAs(imageParent) {
//
//                                }
//                        ) {
//
//                            AsyncImage(
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .clip(shape = RoundedCornerShape(16.dp)),
//                                model = ImageRequest
//                                    .Builder(LocalContext.current)
//                                    .data(item.cover)
//                                    .crossfade(true)
//                                    .build(),
//                                contentDescription = "" ,
//                                contentScale = ContentScale.Crop
//                            )
//
//                        }
//
//                        Spacer(
//                            modifier = Modifier
//                                .size(8.dp)
//                                .constrainAs(spacer1) {
//                                    top.linkTo(imageParent.bottom)
//                                }
//                        )
//
//                        Text(
//                            modifier = Modifier
//                                .constrainAs(title) {
//                                    top.linkTo(spacer1.bottom)
//                                    start.linkTo(imageParent.start)
//                                    end.linkTo(imageParent.end)
//                                    width = Dimension.fillToConstraints
//                                },
//                            text = item.title ,
//                            style = regularFont(12.sp) ,
//                            maxLines = 1 ,
//                            overflow = TextOverflow.Ellipsis ,
//                            textAlign = TextAlign.Center
//                        )
//
//                        if (item.subTitle != null) {
//
//                            Spacer(
//                                modifier = Modifier
//                                    .size(3.dp)
//                                    .constrainAs(spacer2) {
//                                        top.linkTo(title.bottom)
//                                    }
//                            )
//
//                            Text(
//                                modifier = Modifier
//                                    .constrainAs(subTitle) {
//                                        top.linkTo(spacer2.bottom)
//                                        start.linkTo(imageParent.start)
//                                        end.linkTo(imageParent.end)
//                                        bottom.linkTo(parent.bottom)
//                                    } ,
//                                text = item.subTitle!!,
//                                style = regularFont(12.sp , colorTextGray2) ,
//                                maxLines = 1 ,
//                                overflow = TextOverflow.Ellipsis ,
//                                textAlign = TextAlign.Center
//                            )
//
//                        }
//                        else if (item.rate != null) {
//
//                            Spacer(
//                                modifier = Modifier
//                                    .size(4.dp)
//                                    .constrainAs(spacer2) {
//                                        top.linkTo(title.bottom)
//                                    }
//                            )
//
//                            Row(
//                                modifier = Modifier
//                                    .constrainAs(rateParent) {
//                                        top.linkTo(spacer2.bottom)
//                                        start.linkTo(imageParent.start)
//                                        end.linkTo(imageParent.end)
//                                        bottom.linkTo(parent.bottom)
//                                    } ,
//                                verticalAlignment = Alignment.CenterVertically ,
//                                horizontalArrangement = Arrangement.Center
//                            ) {
//
//                                Text(
//                                    modifier = Modifier.offset(y = (0.75).dp) ,
//                                    text = item.rate.toString() ,
//                                    style = boldFont(12.sp , colorYellow) ,
//                                )
//
//                                Spacer(modifier = Modifier.size(3.dp))
//
//                                Image(
//                                    modifier = Modifier
//                                        .width(12.dp)
//                                        .height(12.dp) ,
//                                    painter = painterResource(id = R.drawable.ic_star),
//                                    contentDescription = "" ,
//                                    colorFilter = ColorFilter.tint(colorYellow),
//                                    contentScale = ContentScale.Inside
//                                )
//
//                            }
//
//                        }
//
//                    }

                }

                repeat(3 - subList.size) {
                    Column(
                        modifier = Modifier.weight(1f) ,
                        verticalArrangement = Arrangement.spacedBy(16.dp) ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                    }
                }

            }

        }

        Spacer(modifier = Modifier.size(0.dp))

    }

}