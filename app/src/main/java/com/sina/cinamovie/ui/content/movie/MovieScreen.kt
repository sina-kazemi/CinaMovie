package com.sina.cinamovie.ui.content.movie

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import coil.size.SizeResolver
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.sina.cinamovie.R
import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.res.HomeExtraRes
import com.sina.cinamovie.data.res.HomeRes
import com.sina.cinamovie.data.res.TitleDetailsRes
import com.sina.cinamovie.model.*
import com.sina.cinamovie.ui.content.common.AppBar
import com.sina.cinamovie.ui.content.common.ListHeader
import com.sina.cinamovie.ui.content.list.*
import com.sina.cinamovie.ui.theme.*
import com.sina.cinamovie.util.GetWidthPx
import com.sina.cinamovie.util.getCustomImageWidthUrl
import com.sina.cinamovie.util.toDp
import com.sina.cinamovie.vm.MovieViewModel
import kotlinx.coroutines.delay
import timber.log.Timber
import java.lang.Exception

@Composable
fun MovieScreen(itemId: String ,navController: NavHostController , movieViewModel: MovieViewModel) {

    Timber.d("MovieId :: $itemId")

    LaunchedEffect(true) {
        movieViewModel.fetchMovie(itemId)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val movieFlowLifecycleAware = remember(movieViewModel.movieUiState , lifecycleOwner) {
        movieViewModel.movieUiState.flowWithLifecycle(lifecycleOwner.lifecycle , Lifecycle.State.STARTED)
    }
    val movieRes: Result<ApiResponse<TitleDetailsRes>> by movieFlowLifecycleAware.collectAsState(initial = Result.loading())

    Timber.d("movieRes:::: ${movieRes.status}")

    val movieShowPlaceHolder by remember {
        derivedStateOf { movieRes.status == Result.Status.LOADING }
    }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenWidthPx = with(LocalDensity.current) { screenWidth.toPx() }.toInt()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        var titleStr by remember {
            mutableStateOf("")
        }

        var releaseYearStr by remember {
            mutableStateOf("")
        }

        movieRes.data?.data?.overview?.title?.let {
            titleStr = it
        }

        movieRes.data?.data?.overview?.releaseYear?.let {
            releaseYearStr = it
        }

        AppBar(
            model = AppBarModel(
                title = titleStr ,
                subTitle = releaseYearStr
            ) ,
            navController = navController ,
            showPlaceHolder = movieShowPlaceHolder
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            ConstraintLayout (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, top = 8.dp)
            ) {

                val guideline = createGuidelineFromStart(fraction = 0.666f)
                val (imageParent , detailParent) = createRefs()

                Box(
                    modifier = Modifier
                        .constrainAs(imageParent) {
                            start.linkTo(parent.start)
                            end.linkTo(guideline)
                            top.linkTo(parent.top)
                            width = Dimension.fillToConstraints
                        }
                        .aspectRatio(2f / 3f)
                        .padding(12.dp)
                        .background(
                            shape = RoundedCornerShape(16.dp),
                            color = colorGray.copy(alpha = 0.75f)
                        )
                ) {

                    AsyncImage(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(16.dp))
                            .placeholder(
                                visible = movieShowPlaceHolder,
                                color = colorGray,
                                shape = RoundedCornerShape(16.dp),
                                highlight = PlaceholderHighlight.fade(
                                    highlightColor = colorPlaceHolder
                                )
                            ) ,
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data(movieRes.data?.data?.overview?.cover.toString()
                                .getCustomImageWidthUrl(GetWidthPx()*2/3))
                            .crossfade(true)
                            .size(screenWidthPx*2/3 , screenWidthPx)
                            .build(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )

                }

                ConstraintLayout (
                    modifier = Modifier
                        .constrainAs(detailParent) {
                            end.linkTo(parent.end)
                            top.linkTo(imageParent.top)
                            start.linkTo(guideline)
                            bottom.linkTo(imageParent.bottom)
                            width = Dimension.fillToConstraints
                        }
                        .aspectRatio(1f / 3f)
                ) {

                    val (genreParent , durationParent , rateParent) = createRefs()

                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .aspectRatio(1f / 1f)
                            .constrainAs(genreParent) {
                                top.linkTo(parent.top)
                            }
                            .padding(12.dp)
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = colorGray.copy(alpha = 0.75f)
                            )
                            .placeholder(
                                visible = movieShowPlaceHolder,
                                color = colorGray,
                                shape = RoundedCornerShape(16.dp),
                                highlight = PlaceholderHighlight.fade(
                                    highlightColor = colorPlaceHolder
                                )
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        DetailMovieHeader(
                            painter = painterResource(id = R.drawable.ic_camera_movie),
                            title = stringResource(id = R.string.str_genre),
                            desc = movieRes.data?.data?.overview?.genres?.let {
                                try {it[0].title}
                                catch (e:Exception) {""}
                            }.toString()
                        )

                    }

                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .aspectRatio(1f / 1f)
                            .constrainAs(durationParent) {
                                top.linkTo(genreParent.bottom)
                            }
                            .padding(12.dp)
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = colorGray.copy(alpha = 0.75f)
                            )
                            .placeholder(
                                visible = movieShowPlaceHolder,
                                color = colorGray,
                                shape = RoundedCornerShape(16.dp),
                                highlight = PlaceholderHighlight.fade(
                                    highlightColor = colorPlaceHolder
                                )
                            ) ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        DetailMovieHeader(
                            painter = painterResource(id = R.drawable.ic_time_five),
                            title = stringResource(R.string.str_duration),
                            desc = movieRes.data?.data?.overview?.runtime.toString()
                        )

                    }

                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .aspectRatio(1f / 1f)
                            .constrainAs(rateParent) {
                                top.linkTo(durationParent.bottom)
                            }
                            .padding(12.dp)
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = colorGray.copy(alpha = 0.75f)
                            )
                            .placeholder(
                                visible = movieShowPlaceHolder,
                                color = colorGray,
                                shape = RoundedCornerShape(16.dp),
                                highlight = PlaceholderHighlight.fade(
                                    highlightColor = colorPlaceHolder
                                )
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        DetailMovieHeader(
                            painter = painterResource(id = R.drawable.ic_star),
                            title = stringResource(R.string.str_rating),
                            desc = movieRes.data?.data?.overview?.imdbRating.toString()
                        )

                    }

                }

            }

            Spacer(modifier = Modifier.size(40.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .placeholder(
                        visible = movieShowPlaceHolder,
                        color = colorGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight.fade(
                            highlightColor = colorPlaceHolder
                        )
                    ),
                text = kotlin.run { if (movieShowPlaceHolder) "TestTitle" else {titleStr} } ,
                style = mediumFont(24.sp) ,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.size(12.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .placeholder(
                        visible = movieShowPlaceHolder,
                        color = colorGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight.fade(
                            highlightColor = colorPlaceHolder
                        )
                    ),
                text = movieRes.data?.data?.overview?.plot.toString(),
                style = regularFont(textColor = colorTextGray3) ,
                lineHeight = 24.sp ,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.size(16.dp))

            if (movieShowPlaceHolder) {
                val tempList = listOf(
                    TitleDetailsRes.Overview.Genre("" , "Title") ,
                    TitleDetailsRes.Overview.Genre("" , "Title") ,
                    TitleDetailsRes.Overview.Genre("" , "Title")
                )
                GenreList(genreList = tempList , true)
            }
            else {
                var genreList = listOf<TitleDetailsRes.Overview.Genre>()
                movieRes.data?.data?.overview?.genres?.let {
                    genreList = it
                }
                GenreList(genreList = genreList)
            }

            val configuration = LocalConfiguration.current
            var heightSize by remember { mutableStateOf(IntSize.Zero) }

            Spacer(modifier = Modifier.size(48.dp))
            ListHeader(
                title = stringResource(R.string.str_photos_and_videos) ,
                showMore =  true ,
                showPlaceHolder = movieShowPlaceHolder
            )
            Spacer(modifier = Modifier.size(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()) ,
                verticalAlignment = Alignment.CenterVertically ,
                horizontalArrangement = Arrangement.Start
            ) {

                Spacer(modifier = Modifier.size(24.dp))

                ConstraintLayout (
                    modifier = Modifier
                        .onSizeChanged {
                            heightSize = it
                        }
                ) {

                    val (trailerParent , photosList , videosList) = createRefs()
                    val guideLine = createGuidelineFromTop(0.5f)

                    Box(
                        modifier = Modifier
                            .width(screenWidth / 3)
                            .aspectRatio(2f / 3f)
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = colorGray.copy(alpha = 0.75f)
                            )
                            .placeholder(
                                visible = movieShowPlaceHolder,
                                color = colorGray,
                                shape = RoundedCornerShape(16.dp),
                                highlight = PlaceholderHighlight.fade(
                                    highlightColor = colorPlaceHolder
                                )
                            )
                    ) {

                        val context = LocalContext.current

                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = RoundedCornerShape(16.dp)) ,
                            model = ImageRequest
                                .Builder(LocalContext.current)
                                .data(movieRes.data?.data?.overview?.cover?.getCustomImageWidthUrl(GetWidthPx()/3))
                                .crossfade(true)
                                .size(screenWidthPx/3 , screenWidthPx/2)
                                .build(),
                            contentDescription = "" ,
                            contentScale = ContentScale.Crop
                        )

                        val exoPlayer = remember(context) {
                            ExoPlayer.Builder(context).build().apply {
                                setMediaItems(
                                    mutableListOf(
                                        MediaItem.fromUri("https://videos1.varzeshe3.com/videos-quality/2022/05/02/B/0neh3nks.mp4")
//                            MediaItem.fromUri("https://www.imdb.com/video/vi3877612057")
                                    )
                                )
                                repeatMode = ExoPlayer.REPEAT_MODE_ALL
                                volume = 0f
                                playWhenReady = true
                            }
                        }

                        LaunchedEffect(exoPlayer) {
                            exoPlayer.apply {
                                prepare()
                                play()
                            }
                        }

                        DisposableEffect(
                            AndroidView(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(16.dp)),
                                factory = {
                                    StyledPlayerView(context).apply {
                                        player = exoPlayer
                                        useController = false
                                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                                        FrameLayout.LayoutParams(
                                            ViewGroup.LayoutParams.MATCH_PARENT,
                                            ViewGroup.LayoutParams.MATCH_PARENT
                                        )
                                    }
                                }
                            )
                        ) {
                            onDispose {
                                exoPlayer.release()
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    shape = RoundedCornerShape(16.dp),
                                    color = colorBlack.copy(alpha = 0.8f)
                                ) ,
                            contentAlignment = Alignment.Center
                        ) {

                            Text(text = "Play Trailer" , style = mediumFont(16.sp))

                        }

                    }

                }

                Spacer(modifier = Modifier.size(16.dp))

                Column (
                    modifier = Modifier.height(with(LocalDensity.current) { heightSize.height.toDp() }) ,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    if (movieShowPlaceHolder) {
                        val tempList = listOf(
                            TitleDetailsRes.Photo("" , "" , "") ,
                            TitleDetailsRes.Photo("" , "" , "") ,
                            TitleDetailsRes.Photo("" , "" , "") ,
                            TitleDetailsRes.Photo("" , "" , "") ,
                            TitleDetailsRes.Photo("" , "" , "") ,
                            TitleDetailsRes.Photo("" , "" , "") ,
                            TitleDetailsRes.Photo("" , "" , "") ,
                            TitleDetailsRes.Photo("" , "" , "") ,
                            TitleDetailsRes.Photo("" , "" , "") ,
                            TitleDetailsRes.Photo("" , "" , "")
                        )
                        ImageList(
                            modifier = Modifier.height(with(LocalDensity.current) { heightSize.height.toDp() } / 2 - 8.dp) ,
                            itemSizeDp =  with(LocalDensity.current) { heightSize.height.toDp() } / 2 - 8.dp,
                            imageList = tempList ,
                            showPlaceHolder = true
                        )
                    }
                    else {
                        var imageList = listOf<TitleDetailsRes.Photo>()
                        movieRes.data?.data?.photos?.let {
                            imageList = it
                        }
                        ImageList(
                            modifier = Modifier.height(with(LocalDensity.current) { heightSize.height.toDp() } / 2 - 8.dp) ,
                            itemSizeDp =  with(LocalDensity.current) { heightSize.height.toDp() } / 2 - 8.dp,
                            imageList = imageList.subList(0 , imageList.size.coerceAtMost(10))
                        )
                    }

                    if (movieShowPlaceHolder) {
                        val tempList = listOf(
                            TitleDetailsRes.Video("" , "" , "" , "" , "") ,
                            TitleDetailsRes.Video("" , "" , "" , "" , "") ,
                            TitleDetailsRes.Video("" , "" , "" , "" , "") ,
                            TitleDetailsRes.Video("" , "" , "" , "" , "") ,
                            TitleDetailsRes.Video("" , "" , "" , "" , "") ,
                        )
                        VideoList(
                            modifier = Modifier.height(with(LocalDensity.current) { heightSize.height.toDp() } / 2 - 8.dp) ,
                            itemHeightDp = with(LocalDensity.current) { heightSize.height.toDp() } / 2 - 8.dp ,
                            itemWidthDp = with(LocalDensity.current) { heightSize.height.toDp() } ,
                            videoList = tempList ,
                            showPlaceHolder = true
                        )
                    }
                    else {
                        var videoList = listOf<TitleDetailsRes.Video>()
                        movieRes.data?.data?.videos?.let {
                            videoList = it
                        }
                        VideoList(
                            modifier = Modifier.height(with(LocalDensity.current) { heightSize.height.toDp() } / 2 - 8.dp) ,
                            itemHeightDp = with(LocalDensity.current) { heightSize.height.toDp() } / 2 - 8.dp ,
                            itemWidthDp = with(LocalDensity.current) { heightSize.height.toDp() } ,
                            videoList = videoList.subList(0 , videoList.size.coerceAtMost(5))
                        )
                    }

                }

            }

            Spacer(modifier = Modifier.size(48.dp))
            ListHeader(
                title = stringResource(R.string.str_details) ,
                showMore =  false ,
                showPlaceHolder = movieShowPlaceHolder
            )
            Spacer(modifier = Modifier.size(24.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .background(
                        shape = RoundedCornerShape(16.dp),
                        color = colorGray.copy(alpha = 0.75f)
                    )
                    .placeholder(
                        visible = movieShowPlaceHolder,
                        color = colorGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight.fade(
                            highlightColor = colorPlaceHolder
                        )
                    )
            ) {

                var releaseDate by remember { mutableStateOf("") }
                movieRes.data?.data?.details?.releaseDate?.let {
                    if (it.isNotEmpty()) {
                        releaseDate = it[0].title.toString()
                    }
                }

                var country by remember { mutableStateOf("") }
                movieRes.data?.data?.details?.countryOfOrigin?.let {
                    if (it.isNotEmpty()) {
                        country = it[0].title.toString()
                    }
                }

                var location by remember { mutableStateOf("") }
                movieRes.data?.data?.details?.filmingLocations?.let {
                    if (it.isNotEmpty()) {
                        location = it[0].title.toString()
                    }
                }

                var language by remember { mutableStateOf("") }
                movieRes.data?.data?.details?.language?.let {
                    language = it.joinToString(separator = ".") { item -> item.title.toString() }
                }

                var company by remember { mutableStateOf("") }
                movieRes.data?.data?.details?.productionCompanies?.let {
                    if (it.isNotEmpty()) {
                        company = it[0].title.toString()
                    }
                }

                DetailMovieColumnItem(title = stringResource(R.string.str_released_date), desc = releaseDate)
                DetailMovieColumnItem(title = stringResource(R.string.str_country), desc = country)
                DetailMovieColumnItem(title = stringResource(R.string.str_locations), desc = location)
                DetailMovieColumnItem(title = stringResource(R.string.str_languages), desc = language)
                DetailMovieColumnItem(title = stringResource(R.string.str_companies), desc = company , false)

            }

            Spacer(modifier = Modifier.size(48.dp))
            ListHeader(
                title = stringResource(R.string.str_casts) ,
                showMore =  true ,
                showPlaceHolder = movieShowPlaceHolder)
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                var castList = listOf<TitleDetailsRes.TopCast>()
                if (movieShowPlaceHolder) {
                    castList = listOf(
                        TitleDetailsRes.TopCast("" , "" , "" , "Title" , "Title") ,
                        TitleDetailsRes.TopCast("" , "" , "" , "Title" , "Title") ,
                        TitleDetailsRes.TopCast("" , "" , "" , "Title" , "Title") ,
                        TitleDetailsRes.TopCast("" , "" , "" , "Title" , "Title") ,
                        TitleDetailsRes.TopCast("" , "" , "" , "Title" , "Title") ,
                        TitleDetailsRes.TopCast("" , "" , "" , "Title" , "Title") ,
                        TitleDetailsRes.TopCast("" , "" , "" , "Title" , "Title") ,
                        TitleDetailsRes.TopCast("" , "" , "" , "Title" , "Title") ,
                        TitleDetailsRes.TopCast("" , "" , "" , "Title" , "Title")
                    )
                }
                else {
                    movieRes.data?.data?.topCasts?.let {
                        castList = it.subList(0 , it.size.coerceAtMost(12))
                    }
                }

                castList.windowed(3 , 3, true).forEach { subList ->

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)) {

                        subList.forEach {

                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp) ,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                AsyncImage(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f / 1f)
                                        .clip(shape = RoundedCornerShape(16.dp))
                                        .background(
                                            shape = RoundedCornerShape(16.dp),
                                            color = colorGray.copy(alpha = 0.75f)
                                        )
                                        .placeholder(
                                            visible = movieShowPlaceHolder,
                                            color = colorGray,
                                            shape = RoundedCornerShape(16.dp),
                                            highlight = PlaceholderHighlight.fade(
                                                highlightColor = colorPlaceHolder
                                            )
                                        ),
                                    model = ImageRequest
                                        .Builder(LocalContext.current)
                                        .data(it.image?.getCustomImageWidthUrl(GetWidthPx()/3))
                                        .crossfade(true)
                                        .size(screenWidthPx/3 , screenWidthPx/3)
                                        .build(),
                                    contentDescription = "" ,
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.size(8.dp))

                                Text(
                                    modifier = Modifier
                                        .placeholder(
                                            visible = movieShowPlaceHolder,
                                            color = colorGray,
                                            shape = RoundedCornerShape(16.dp),
                                            highlight = PlaceholderHighlight.fade(
                                                highlightColor = colorPlaceHolder
                                            )
                                        ),
                                    text = it.realName.toString() ,
                                    style = regularFont() ,
                                    maxLines = 1 ,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.size(2.dp))

                                Text(
                                    modifier = Modifier
                                        .placeholder(
                                            visible = movieShowPlaceHolder,
                                            color = colorGray,
                                            shape = RoundedCornerShape(16.dp),
                                            highlight = PlaceholderHighlight.fade(
                                                highlightColor = colorPlaceHolder
                                            )
                                        ) ,
                                    text = "As ${it.movieName}" ,
                                    style = regularFont(12.sp , colorTextGray2) ,
                                    maxLines = 1 ,
                                    overflow = TextOverflow.Ellipsis
                                )

                            }


                        }

                        repeat(3-subList.size) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp) ,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){}
                        }

                    }

                }

            }

            Spacer(modifier = Modifier.size(48.dp))
            ListHeader(
                title = stringResource(R.string.str_more_like_this) ,
                showMore =  true ,
                showPlaceHolder = movieShowPlaceHolder
            )
            Spacer(modifier = Modifier.size(24.dp))

            if (movieShowPlaceHolder) {
                val tempList = listOf(
                    HomeExtraRes.FanPicksTitle("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , 0),
                    HomeExtraRes.FanPicksTitle("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , 0),
                    HomeExtraRes.FanPicksTitle("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , 0),
                    HomeExtraRes.FanPicksTitle("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , 0),
                    HomeExtraRes.FanPicksTitle("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , 0),
                    HomeExtraRes.FanPicksTitle("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , 0),
                    HomeExtraRes.FanPicksTitle("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , 0),
                    HomeExtraRes.FanPicksTitle("" , "" , 0f , "" , "" , "" , 0 , "" , "" , "" , 0)
                )

                MovieList(movieList = tempList ,
                    navController = navController , showPlaceHolder = true)

            }
            else {

                movieRes.data?.data?.relatedMovies?.let {
                    MovieList(movieList = it.subList(0 , it.size.coerceAtMost(12)) ,
                        navController = navController)
                }

            }

            Spacer(modifier = Modifier.size(24.dp))

        }

    }

}

@Composable
private fun DetailMovieHeader(painter: Painter, title: String, desc: String) {

    Spacer(modifier = Modifier.size(16.dp))

    Image(
        modifier = Modifier
            .width(20.dp)
            .height(20.dp),
        painter = painter,
        contentDescription = "",
        colorFilter = ColorFilter.tint(colorWhite)
    )

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 4.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            verticalArrangement = Arrangement.Center ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = title , style = regularFont(14.sp , colorTextGray2) , maxLines = 1 , overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.size(2.dp))
            Text(text = desc , style = boldFont(16.sp , colorWhite) , maxLines = 1 , overflow = TextOverflow.Ellipsis)

        }

    }

}

@Composable
private fun DetailMovieColumnItem(title: String , desc: String , showDivider: Boolean = true) {

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 18.dp) ,
            horizontalArrangement = Arrangement.SpaceBetween ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            
            Text(text = title, style = regularFont(14.sp))

            Spacer(modifier = Modifier.size(12.dp))

            Text(text = desc, style = regularFont(14.sp, colorTextGray2) , maxLines = 1 , overflow = TextOverflow.Ellipsis)
            
        }

        if (showDivider) {

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 12.dp),
                color = colorDivider
            )
            
        }

    }

}