package com.sina.cinamovie.ui.content.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sina.cinamovie.model.GenreModel
import com.sina.cinamovie.ui.theme.colorGray
import com.sina.cinamovie.util.highResolutionImage

@Composable
fun GenreScreen(genreList: List<GenreModel>) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()) ,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Spacer(modifier = Modifier.size(0.dp))

        genreList.windowed(2 , 2 , true).forEach { subList ->

            Row (
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){

                subList.forEach { genreModel ->

                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .aspectRatio(3f / 2f)
                            .clip(shape = RoundedCornerShape(16.dp))
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = colorGray.copy(alpha = 0.75f)
                            ),
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data(genreModel.image.highResolutionImage())
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