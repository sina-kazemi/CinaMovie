package com.sina.cinamovie.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.repo.MovieRepository
import com.sina.cinamovie.data.res.TitleDetailsRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _movieUiState = MutableStateFlow<Result<ApiResponse<TitleDetailsRes>>>(Result.loading())
    val movieUiState: StateFlow<Result<ApiResponse<TitleDetailsRes>>> = _movieUiState

    fun clearState() {
        _movieUiState.value = Result.loading()
    }

    fun fetchMovie(itemId: String) {
        viewModelScope.launch {
            movieRepository.fetchMovie(itemId).collect{
                _movieUiState.value = it
            }
        }
    }

}