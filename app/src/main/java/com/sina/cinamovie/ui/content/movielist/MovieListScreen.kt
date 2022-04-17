package com.sina.cinamovie.ui.content.movielist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.sina.cinamovie.model.AppBarModel
import com.sina.cinamovie.model.MovieModel
import com.sina.cinamovie.ui.content.common.AppBar
import com.sina.cinamovie.ui.content.list.MovieItem

@Composable
fun MovieListScreen(title: String , items: List<MovieModel> , navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        AppBar(model = AppBarModel(title = title), navController = navController)

        GridList(items = items, navController = navController)

    }

}

@Composable
fun GridList(items: List<MovieModel> , navController: NavHostController) {

    Column(modifier = Modifier.fillMaxSize()) {

        items.windowed(3 , 3 , true).forEach { subList ->

            Row(modifier = Modifier.fillMaxWidth()) {

                subList.forEach {

                    MovieItem(item = it, navController = navController, isGridList = true)

                }

            }

        }

    }

}