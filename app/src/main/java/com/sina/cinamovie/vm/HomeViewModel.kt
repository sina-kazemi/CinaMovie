package com.sina.cinamovie.vm

import androidx.lifecycle.ViewModel
import com.sina.cinamovie.data.remote.HomeRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    homeRemoteDataSource: HomeRemoteDataSource
): ViewModel() {



}