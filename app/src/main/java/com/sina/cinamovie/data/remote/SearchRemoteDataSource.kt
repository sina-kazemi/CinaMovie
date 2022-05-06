package com.sina.cinamovie.data.remote

import com.sina.cinamovie.api.ApiService
import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.getResponse
import com.sina.cinamovie.data.res.GenresRes
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(private val apiService: ApiService){

    suspend fun fetchGenres(): Result<ApiResponse<List<GenresRes>>> = getResponse { apiService.getGenres() }

}