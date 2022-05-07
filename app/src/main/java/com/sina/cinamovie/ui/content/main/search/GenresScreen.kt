package com.sina.cinamovie.ui.content.main.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.res.GenresRes
import com.sina.cinamovie.ui.navigation.BottomNavItem
import com.sina.cinamovie.ui.theme.colorGray
import com.sina.cinamovie.util.GetWidthPx
import com.sina.cinamovie.util.ITEM_ID
import com.sina.cinamovie.util.getCustomImageWidthUrl
import com.sina.cinamovie.util.getOriginalImageSizeUrl
import com.sina.cinamovie.vm.SearchViewModel

@Composable
fun GenreScreen(navController: NavController , searchViewModel: SearchViewModel) {

    LaunchedEffect(true) {
        searchViewModel.fetchGenres()
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val genreFlowLifecycleAware = remember(searchViewModel.genresUiState, lifecycleOwner) {
        searchViewModel.genresUiState.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val genresRes: Result<ApiResponse<List<GenresRes>>> by genreFlowLifecycleAware.collectAsState(initial = Result.loading())

    val showPlaceHolder by remember {
        derivedStateOf { genresRes.status == Result.Status.LOADING }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()) ,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Spacer(modifier = Modifier.size(0.dp))

        val genreList: MutableList<GenresRes> = mutableListOf()

        if (showPlaceHolder) {
            genreList.add(GenresRes("" , ""))
            genreList.add(GenresRes("" , ""))
            genreList.add(GenresRes("" , ""))
            genreList.add(GenresRes("" , ""))
            genreList.add(GenresRes("" , ""))
            genreList.add(GenresRes("" , ""))
            genreList.add(GenresRes("" , ""))
            genreList.add(GenresRes("" , ""))
        }
        else {
            genresRes.data?.data?.let {
                genreList.addAll(it)
            }
        }

        genreList.windowed(2 , 2 , true).forEach { subList ->

            Row (
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){

                subList.forEach { genreModel ->

                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                navController.navigate("${BottomNavItem.MovieList.screen_route}/${genreModel.genre}")
                            }
                            .weight(1f)
                            .aspectRatio(3f / 2f)
                            .clip(shape = RoundedCornerShape(16.dp))
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = colorGray.copy(alpha = 0.75f)
                            ),
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data(genreModel.image.getOriginalImageSizeUrl())
                            .crossfade(true)
                            .build() ,
                        contentScale = ContentScale.Crop ,
                        contentDescription = ""
                    )

                }

                repeat(2 - subList.size) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .aspectRatio(3f / 2f)
                    )
                }

            }

        }

        Spacer(modifier = Modifier.size(0.dp))

    }

}