package com.sina.cinamovie.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.remote.HomeRemoteDataSource
import com.sina.cinamovie.data.repo.HomeRepository
import com.sina.cinamovie.data.res.HomeExtraRes
import com.sina.cinamovie.data.res.HomeRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {

    private val _homeUiState = MutableStateFlow<Result<ApiResponse<HomeRes>>>(Result.loading())
    val homeUiState: StateFlow<Result<ApiResponse<HomeRes>>> = _homeUiState

    private val _homeExtraUiState = MutableStateFlow<Result<ApiResponse<HomeExtraRes>>>(Result.loading())
    val homeExtraUiState: StateFlow<Result<ApiResponse<HomeExtraRes>>> = _homeExtraUiState

    init {
        fetchHomeUiData()
        fetchHomeExtraUiData()
    }

    private fun fetchHomeUiData() {

        viewModelScope.launch {
            homeRepository.fetchHome().collect {
                Timber.d("DataCreated :: $it")
                _homeUiState.value = it
            }
        }

    }

    private fun fetchHomeExtraUiData() {

        viewModelScope.launch {
            homeRepository.fetchHomeExtra().collect{
                _homeExtraUiState.value = it
            }
        }

    }

}