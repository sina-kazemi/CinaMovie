package com.sina.cinamovie.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.repo.SearchRepository
import com.sina.cinamovie.data.res.GenresRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    private var _genresUiState = MutableStateFlow<Result<ApiResponse<List<GenresRes>>>>(Result.loading())
    val genresUiState: StateFlow<Result<ApiResponse<List<GenresRes>>>> = _genresUiState

    fun fetchGenres() {
        viewModelScope.launch {
            searchRepository.fetchGenres().collect{
                _genresUiState.value = it
            }
        }
    }

}