package com.sina.cinamovie.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.repo.ChartRepository
import com.sina.cinamovie.data.res.ChartBoxOfficeRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val chartRepository: ChartRepository
) : ViewModel() {

    private val _boxOfficeUiState = MutableStateFlow<Result<ApiResponse<ChartBoxOfficeRes>>>(Result.loading())
    val boxOfficeUiState: StateFlow<Result<ApiResponse<ChartBoxOfficeRes>>> = _boxOfficeUiState

    init {
        fetchBoxOffice()
    }

    private fun fetchBoxOffice() {
        viewModelScope.launch {
            chartRepository.fetchBoxOffice().collect {
                _boxOfficeUiState.value = it
            }
        }
    }

}