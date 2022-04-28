package com.sina.cinamovie.api

import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.res.HomeExtraRes
import com.sina.cinamovie.data.res.HomeRes
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("home/")
    suspend fun getHome(): Response<ApiResponse<HomeRes>>

    @GET("home/extra")
    suspend fun getHomeExtra(): Response<ApiResponse<HomeExtraRes>>

}