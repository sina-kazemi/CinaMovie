package com.sina.cinamovie.data.remote

import com.sina.cinamovie.api.ApiService
import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.getResponse
import com.sina.cinamovie.data.res.HomeExtraRes
import com.sina.cinamovie.data.res.HomeRes
import retrofit2.Retrofit
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(var apiService: ApiService) {

    suspend fun fetchHome(): Result<ApiResponse<HomeRes>> = getResponse(request = { apiService.getHome() })
    suspend fun fetchHomeExtra(): Result<ApiResponse<HomeExtraRes>> = getResponse(request = { apiService.getHomeExtra() })

}