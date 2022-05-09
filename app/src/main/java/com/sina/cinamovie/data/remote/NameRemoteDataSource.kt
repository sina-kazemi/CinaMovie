package com.sina.cinamovie.data.remote

import com.sina.cinamovie.api.ApiService
import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.getResponse
import com.sina.cinamovie.data.res.NameDetailRes
import javax.inject.Inject

class NameRemoteDataSource @Inject constructor(var apiService: ApiService) {

    suspend fun fetchName(itemId: String): Result<ApiResponse<NameDetailRes>> = getResponse { apiService.getNameDetails(itemId) }

}