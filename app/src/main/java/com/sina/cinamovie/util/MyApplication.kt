package com.sina.cinamovie.util

import android.app.Application
import com.sina.cinamovie.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

    }

}