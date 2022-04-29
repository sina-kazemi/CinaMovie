package com.sina.cinamovie.data.remote

import com.sina.cinamovie.api.ApiService
import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.getResponse
import com.sina.cinamovie.data.res.TitleDetailsRes
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun fetchMovie(itemId: String): Result<ApiResponse<TitleDetailsRes>> = getResponse { apiService.getTitleDetails(itemId) }

}