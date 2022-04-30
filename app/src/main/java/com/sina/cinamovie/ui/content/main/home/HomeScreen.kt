package com.sina.cinamovie.ui.content.main.home

import androidx.compose.foundation.*
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sina.cinamovie.R
import com.sina.cinamovie.data.res.HomeRes
import com.sina.cinamovie.model.MovieModel
import com.sina.cinamovie.model.NewsModel
import com.sina.cinamovie.model.UserModel
import com.sina.cinamovie.ui.content.common.ListHeader
import com.sina.cinamovie.ui.content.list.IMDbOriginalsList
import com.sina.cinamovie.ui.content.list.MovieList
import com.sina.cinamovie.ui.content.list.TrailerList
import com.sina.cinamovie.ui.navigation.BottomNavItem
import com.sina.cinamovie.ui.theme.*
import com.sina.cinamovie.vm.HomeViewModel
import com.sina.cinamovie.data.ApiResponse
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.res.ChartBoxOfficeRes
import com.sina.cinamovie.data.res.HomeExtraRes
import com.sina.cinamovie.util.getAge
import com.sina.cinamovie.vm.ChartViewModel
import timber.log.Timber

@Composable
fun HomeScreen(
    navController: NavHostController ,
    homeViewModel: HomeViewModel ,
    chartViewModel: ChartViewModel
) {

    Timber.d("HomeScreen!!!")

    val lifecycleOwner = LocalLifecycleOwner.current
    val homeFlowLifecycleAware = remember(homeViewModel.homeUiState, lifecycleOwner) {
        homeViewModel.homeUiState.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val homeRes: Result<ApiResponse<HomeRes>> by homeFlowLifecycleAware.collectAsState(initial = Result.loading())

    val homeExtraFlowLifecycleAware = remember(homeViewModel.homeExtraUiState, lifecycleOwner) {
        homeViewModel.homeExtraUiState.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val homeExtraRes: Result<ApiResponse<HomeExtraRes>> by homeExtraFlowLifecycleAware.collectAsState(initial = Result.loading())

    val chartFlowLifecycleAware = remember(chartViewModel.boxOfficeUiState , lifecycleOwner) {
        chartViewModel.boxOfficeUiState.flowWithLifecycle(lifecycleOwner.lifecycle , Lifecycle.State.STARTED)
    }
    val chartRes: Result<ApiResponse<ChartBoxOfficeRes>> by chartFlowLifecycleAware.collectAsState(
        initial = Result.loading()
    )

    val homeResShowPlaceHolder by remember {
        derivedStateOf { homeRes.status == Result.Status.LOADING }
    }

    val homeExtraShowPlaceHolder by remember {
        derivedStateOf { homeExtraRes.status == Result.Status.LOADING }
    }

    val chartShowPlaceHolder by remember {
        derivedStateOf { chartRes.status == Result.Status.LOADING }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.Transparent)
    ) {

        Spacer(modifier = Modifier.size(32.dp))

        HomeAppbar(userModel = UserModel(firstName = "John"))

        Spacer(modifier = Modifier.size(24.dp))

        SearchComponent()

        Spacer(modifier = Modifier.size(32.dp))

        if (homeResShowPlaceHolder) {
            val tempList = listOf(
                HomeRes.Trailer("" , "" , "" , "" , "" , "" , "") ,
                HomeRes.Trailer("" , "" , "" , "" , "" , "" , "") ,
                HomeRes.Trailer("" , "" , "" , "" , "" , "" , "") ,
                HomeRes.Trailer("" , "" , "" , "" , "" , "" , "") ,
                HomeRes.Trailer("" , "" , "" , "" , "" , "" , "") ,
                HomeRes.Trailer("" , "" , "" , "" , "" , "" , "") ,
                HomeRes.Trailer("" , "" , "" , "" , "" , "" , "") ,
                HomeRes.Trailer("" , "" , "" , "" , "" , "" , "") ,
            )

            TrailerList(
                trailerList = tempList ,
                showPlaceHolder = true)
        }
        else {

            homeRes.data?.data?.trailers.let {
                if (it != null) {
                    TrailerList(trailerList = it.subList(0 , it.size.coerceAtMost(8)))
                }
            }

        }

        Spacer(modifier = Modifier.size(40.dp))

        ListHeader(
            title = stringResource(R.string.str_fan_favorites) ,
            showMore = true ,
            showPlaceHolder = homeExtraShowPlaceHolder)
        Spacer(modifier = Modifier.size(24.dp))
        if (homeExtraShowPlaceHolder) {
            val tempMovieList = listOf(
                HomeExtraRes.FanPicksTitle("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , 0),
                HomeExtraRes.FanPicksTitle("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , 0),
                HomeExtraRes.FanPicksTitle("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , 0),
                HomeExtraRes.FanPicksTitle("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , 0),
                HomeExtraRes.FanPicksTitle("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , 0),
                HomeExtraRes.FanPicksTitle("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , 0),
                HomeExtraRes.FanPicksTitle("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , 0),
                HomeExtraRes.FanPicksTitle("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , 0)
            )
            MovieList(movieList = tempMovieList, navController = navController , true)
        }
        else {
            homeExtraRes.data?.data?.fanPicksTitles?.let {
                MovieList(movieList = it.subList(0 , it.size.coerceAtMost(12)),
                    navController = navController
                )
            }
        }


        Spacer(modifier = Modifier.size(48.dp))

        ListHeader(
            title = stringResource(R.string.str_coming_soon) ,
            showMore = true ,
            showPlaceHolder = homeExtraShowPlaceHolder
        )
        Spacer(modifier = Modifier.size(24.dp))
        if (homeExtraShowPlaceHolder) {
            val tempComingSoonMovies = listOf(
                HomeExtraRes.ComingSoonMovie("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , "" , "" , 0 , 0),
                HomeExtraRes.ComingSoonMovie("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , "" , "" , 0 , 0),
                HomeExtraRes.ComingSoonMovie("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , "" , "" , 0 , 0),
                HomeExtraRes.ComingSoonMovie("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , "" , "" , 0 , 0),
                HomeExtraRes.ComingSoonMovie("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , "" , "" , 0 , 0),
                HomeExtraRes.ComingSoonMovie("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , "" , "" , 0 , 0),
                HomeExtraRes.ComingSoonMovie("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , "" , "" , 0 , 0),
                HomeExtraRes.ComingSoonMovie("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , "" , "" , 0 , 0)
            )

            MovieList(movieList = tempComingSoonMovies, navController = navController , showPlaceHolder = true)
        }
        else {
            homeExtraRes.data?.data?.comingSoonMovies?.let {
                MovieList(movieList = it.subList(0 , it.size.coerceAtMost(12)) ,
                    navController = navController)
            }
        }

        Spacer(modifier = Modifier.size(48.dp))

        if (homeResShowPlaceHolder) {
            val tempList = listOf(
                HomeRes.ImdbOriginal("" , "" , "" , false , "" , "" , "" , false),
                HomeRes.ImdbOriginal("" , "" , "" , false , "" , "" , "" , false),
                HomeRes.ImdbOriginal("" , "" , "" , false , "" , "" , "" , false),
                HomeRes.ImdbOriginal("" , "" , "" , false , "" , "" , "" , false),
                HomeRes.ImdbOriginal("" , "" , "" , false , "" , "" , "" , false),
                HomeRes.ImdbOriginal("" , "" , "" , false , "" , "" , "" , false),
                HomeRes.ImdbOriginal("" , "" , "" , false , "" , "" , "" , false),
                HomeRes.ImdbOriginal("" , "" , "" , false , "" , "" , "" , false)
            )

            IMDbOriginalsList(imdbOriginalList = tempList , showPlaceHolder = true)
        }
        else {
            homeRes.data?.data?.imdbOriginals?.let {
                Timber.d("IMDB_ORIGINALS:: ${it.toString()}")
//                IMDbOriginalsList(imdbOriginalList = it.subList(0 , it.size.coerceAtMost(8)))
            }
        }

        Spacer(modifier = Modifier.size(48.dp))

        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp

        ListHeader(
            title = stringResource(R.string.str_news) ,
            showMore = true ,
            showPlaceHolder = homeResShowPlaceHolder
        )
        Spacer(modifier = Modifier.size(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()) ,
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Spacer(modifier = Modifier.size(0.dp))

            var tempList = listOf<HomeRes.News>()

            if (homeResShowPlaceHolder) {
                tempList = listOf(
                    HomeRes.News("" , "" , "" , "" , ""),
                    HomeRes.News("" , "" , "" , "" , ""),
                    HomeRes.News("" , "" , "" , "" , ""),
                    HomeRes.News("" , "" , "" , "" , ""),
                    HomeRes.News("" , "" , "" , "" , ""),
                    HomeRes.News("" , "" , "" , "" , ""),
                    HomeRes.News("" , "" , "" , "" , ""),
                    HomeRes.News("" , "" , "" , "" , ""),
                    HomeRes.News("" , "" , "" , "" , ""),
                    HomeRes.News("" , "" , "" , "" , "")
                )
            }
            else {
                homeRes.data?.data?.news?.let {
                    tempList = it
                }
            }

            tempList.let {
                it.subList(0 , it.size.coerceAtMost(12)).windowed(2 , 2 , true).forEach { subList ->

                    Column(
                        modifier = Modifier
                            .width(screenWidth - 48.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        subList.forEach {

                            ConstraintLayout(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .placeholder(
                                        visible = homeResShowPlaceHolder,
                                        color = colorGray,
                                        shape = RoundedCornerShape(16.dp),
                                        highlight = PlaceholderHighlight.fade(
                                            highlightColor = colorPlaceHolder
                                        )
                                    ),
                            ) {

                                val (imageParent , detailParent) = createRefs()

                                AsyncImage(
                                    modifier = Modifier
                                        .aspectRatio(1f / 1f)
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(
                                            shape = RoundedCornerShape(16.dp),
                                            color = colorGray.copy(alpha = 0.75f)
                                        )
                                        .constrainAs(imageParent) {
                                            top.linkTo(detailParent.top)
                                            bottom.linkTo(detailParent.bottom)
//                                        width = Dimension.fillToConstraints
                                            height = Dimension.fillToConstraints
                                        },
                                    model = ImageRequest
                                        .Builder(LocalContext.current)
                                        .data(it.image)
                                        .crossfade(true)
                                        .build(),
                                    contentScale = ContentScale.Crop ,
                                    contentDescription = ""
                                )

                                Column(
                                    modifier = Modifier
                                        .padding(start = 16.dp)
                                        .constrainAs(detailParent) {
                                            start.linkTo(imageParent.end)
                                            top.linkTo(parent.top)
                                            bottom.linkTo(parent.bottom)
                                            end.linkTo(parent.end)
                                            width = Dimension.fillToConstraints
//                                        height = Dimension.fillToConstraints
                                        },
                                    horizontalAlignment = Alignment.Start ,
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    Spacer(modifier = Modifier.size(6.dp))

                                    Text(
                                        text = it.source.toString() ,
                                        style = regularFont(12.sp , colorTextGray2) ,
                                        maxLines = 1 ,
                                        overflow = TextOverflow.Ellipsis ,
                                        textAlign = TextAlign.Start
                                    )

                                    Spacer(modifier = Modifier.size(4.dp))

                                    Column {

//                                    var lines by remember { mutableStateOf(0) }
                                        val lineHeight = 14.sp*4/3

                                        Text(
                                            modifier = Modifier
                                                .sizeIn(minHeight = with(LocalDensity.current) {
                                                    (lineHeight*2).toDp()
                                                }),
                                            text = it.title.toString() ,
                                            style = regularFont() ,
                                            maxLines = 2 ,
                                            overflow = TextOverflow.Ellipsis ,
                                            textAlign = TextAlign.Start,
                                            lineHeight = lineHeight
                                        )

//                                    repeat(2 - lines.coerceAtMost(2)) {
//                                        Text(text = "", style = regularFont())
//                                    }

                                    }

                                    Spacer(modifier = Modifier.size(8.dp))

                                    Text(
                                        text = it.date.toString() ,
                                        style = regularFont(12.sp , colorTextGray2) ,
                                        maxLines = 1 ,
                                        overflow = TextOverflow.Ellipsis ,
                                        textAlign = TextAlign.Start
                                    )

                                    Spacer(modifier = Modifier.size(6.dp))

                                }

                            }

                        }

                    }

                }
            }

            Spacer(modifier = Modifier.size(0.dp))

        }

        Spacer(modifier = Modifier.size(48.dp))
        ListHeader(
            title = stringResource(R.string.str_burn_today) ,
            showMore =  true ,
            showPlaceHolder =  homeExtraShowPlaceHolder
        )
        Spacer(modifier = Modifier.size(24.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp) ,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            var tempList = listOf<HomeExtraRes.BornToday>()

            if (homeExtraShowPlaceHolder) {
                tempList = listOf(
                    HomeExtraRes.BornToday("" , "" , "" , "" , "testTitle"),
                    HomeExtraRes.BornToday("" , "" , "" , "" , "testTitle"),
                    HomeExtraRes.BornToday("" , "" , "" , "" , "testTitle"),
                    HomeExtraRes.BornToday("" , "" , "" , "" , "testTitle"),
                    HomeExtraRes.BornToday("" , "" , "" , "" , "testTitle"),
                    HomeExtraRes.BornToday("" , "" , "" , "" , "testTitle"),
                    HomeExtraRes.BornToday("" , "" , "" , "" , "testTitle"),
                    HomeExtraRes.BornToday("" , "" , "" , "" , "testTitle")
                )
            }
            else {
                homeExtraRes.data?.data?.bornTodayList?.let{
                    tempList = it
                }
            }

            tempList.let {
                it.subList(0 , it.size.coerceAtMost(8)).windowed(4 , 4 , true).forEach { subList ->

                    Row (
                        modifier = Modifier.fillMaxWidth() ,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){

                        subList.forEach {

                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        navController.navigate("${BottomNavItem.Person.screen_route}/${it.nameId}")
                                    } ,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                AsyncImage(
                                    modifier = Modifier
                                        .aspectRatio(1f / 1f)
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(
                                            shape = RoundedCornerShape(16.dp),
                                            color = colorGray
                                        )
                                        .placeholder(
                                            visible = homeExtraShowPlaceHolder,
                                            color = colorGray,
                                            shape = RoundedCornerShape(16.dp),
                                            highlight = PlaceholderHighlight.fade(
                                                highlightColor = colorPlaceHolder
                                            )
                                        ),
                                    model = ImageRequest
                                        .Builder(LocalContext.current)
                                        .data(it.image)
                                        .crossfade(true)
                                        .build() ,
                                    contentDescription = "" ,
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.size(8.dp))

                                Text(
                                    modifier = Modifier.placeholder(
                                        visible = homeExtraShowPlaceHolder,
                                        color = colorGray,
                                        shape = RoundedCornerShape(16.dp),
                                        highlight = PlaceholderHighlight.fade(
                                            highlightColor = colorPlaceHolder
                                        )
                                    ) ,
                                    text = it.title.toString(),
                                    style = regularFont(),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.size(2.dp))

                                Text(
                                    modifier = Modifier.placeholder(
                                        visible = homeExtraShowPlaceHolder,
                                        color = colorGray,
                                        shape = RoundedCornerShape(16.dp),
                                        highlight = PlaceholderHighlight.fade(
                                            highlightColor = colorPlaceHolder
                                        )
                                    ),
                                    text = "${it.birth.toString().getAge()} Years Old" ,
                                    style = regularFont(12.sp , colorTextGray2),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                            }

                        }

                        repeat(4 - subList.size) {
                            Column(
                                modifier = Modifier.weight(1f) ,
                                verticalArrangement = Arrangement.spacedBy(16.dp) ,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                            }
                        }

                    }

                }
            }

        }

        Spacer(modifier = Modifier.size(48.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .placeholder(
                    visible = chartShowPlaceHolder,
                    color = colorGray,
                    highlight = PlaceholderHighlight.fade(
                        highlightColor = colorPlaceHolder
                    )
                )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorBlack.copy(alpha = 0.75f))
                    .padding(vertical = 32.dp) ,
                verticalAlignment = Alignment.CenterVertically ,
                horizontalArrangement = Arrangement.Start
            ) {

                Box{

                    Column(
                        modifier = Modifier.padding(horizontal = 32.dp),
                        verticalArrangement = Arrangement.Center ,
                        horizontalAlignment = Alignment.Start
                    ) {

                        Text(text = stringResource(R.string.str_box), style = mediumFont(16.sp))
                        Text(text = stringResource(R.string.str_office), style = extraLightFont(16.sp))

                    }

                }

                Column (
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ){

                    var movieList = listOf<ChartBoxOfficeRes.BoxOfficeTitle>()
                    chartRes.data?.data?.boxOfficeTitles?.let {
                        movieList = it.sortedByDescending { item ->
                            item.gross
                                .replace("M" , "")
                                .replace("m" , "")
                                .replace("$" , "")
                                .toFloat()
                        }
                    }

                    val newMovieList = if (chartShowPlaceHolder) {
                        listOf(
                            ChartBoxOfficeRes.BoxOfficeTitle("" , "" , "" , "" , "" ,""),
                            ChartBoxOfficeRes.BoxOfficeTitle("" , "" , "" , "" , "" ,""),
                            ChartBoxOfficeRes.BoxOfficeTitle("" , "" , "" , "" , "" ,"")
                        )
                    } else {
                        movieList.subList(0 , movieList.size.coerceAtMost(6))
                    }

                    newMovieList.forEachIndexed { index, movieModel ->

                        Column(
                            horizontalAlignment = Alignment.Start ,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {

                            Row(
                                modifier = Modifier.wrapContentWidth() ,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    text = run {
                                        if (index < 9) {
                                            "0${(index+1)}"
                                        }
                                        else {
                                            (index+1).toString()
                                        }
                                    } ,
                                    style = boldFont(20.sp)
                                )

                                Spacer(modifier = Modifier.size(16.dp))

                                Column(
                                    horizontalAlignment = Alignment.Start
                                ) {

                                    Text(text = movieModel.title.toString() , style = regularFont())
                                    Text(text = movieModel.gross.toString() , style = regularFont(12.sp , colorTextGray2))

                                }

                            }

                            if (index < newMovieList.size - 1) {
                                Box(
                                    modifier = Modifier
                                        .padding(start = 11.dp)
                                        .size(2.dp, 32.dp)
                                        .background(
                                            shape = RoundedCornerShape(16.dp),
                                            color = colorYellow
                                        )
                                )
                            }

                        }

                    }

                }

            }


        }

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
            if (userModel.avatar.trim() == "") {
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
                        .data(userModel.avatar)
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
