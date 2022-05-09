package com.sina.cinamovie.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.repo.NameRepository
import com.sina.cinamovie.data.res.NameDetailRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NameViewModel @Inject constructor(var nameRepository: NameRepository): ViewModel() {

    private var _nameUiState = MutableStateFlow<Result<ApiResponse<NameDetailRes>>>(Result.loading())
    val nameUiState: StateFlow<Result<ApiResponse<NameDetailRes>>> = _nameUiState

    fun fetchName(itemId: String) {
        viewModelScope.launch {
            nameRepository.fetchName(itemId).collect{
                _nameUiState.value = it
            }
        }
    }

}