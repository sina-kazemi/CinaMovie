package com.sina.cinamovie.ui.content.main.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sina.cinamovie.R
import com.sina.cinamovie.ui.theme.colorGray
import com.sina.cinamovie.ui.theme.colorWhite
import com.sina.cinamovie.ui.theme.mediumFont

@Composable
fun KeyWordsScreen(wordsList: List<String>) {

    LazyColumn(
        modifier = Modifier.fillMaxSize() ,
        contentPadding = PaddingValues(16.dp) ,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(wordsList) { wordModel ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        shape = RoundedCornerShape(16.dp) ,
                        color = colorGray.copy(alpha = 0.75f)
                    ) ,
                horizontalArrangement = Arrangement.SpaceBetween ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                
                Text(
                    modifier = Modifier.padding(16.dp) ,
                    text = wordModel ,
                    style = mediumFont() ,
                    maxLines = 1 ,
                    overflow = TextOverflow.Ellipsis
                )

                Image(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(20.dp , 20.dp) ,
                    painter = painterResource(id = R.drawable.ic_chevron_right) ,
                    contentDescription = "" ,
                    colorFilter = ColorFilter.tint(colorWhite)
                )

            }

        }

    }

}