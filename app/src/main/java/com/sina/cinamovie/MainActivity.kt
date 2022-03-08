package com.sina.cinamovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.sina.cinamovie.ui.home.AllContentHome
import com.sina.cinamovie.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CinaMovieTheme {
                ProvideWindowInsets {
                    AllContentHome()
                }
            }
        }
    }
}
