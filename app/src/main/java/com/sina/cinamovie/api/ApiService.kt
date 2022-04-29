package com.sina.cinamovie.api

import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.res.ChartBoxOfficeRes
import com.sina.cinamovie.data.res.HomeExtraRes
import com.sina.cinamovie.data.res.HomeRes
import com.sina.cinamovie.data.res.TitleDetailsRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("home/")
    suspend fun getHome(): Response<ApiResponse<HomeRes>>

    @GET("home/extra")
    suspend fun getHomeExtra(): Response<ApiResponse<HomeExtraRes>>

    @GET("chart/boxoffice")
    suspend fun getChartBoxOffice(): Response<ApiResponse<ChartBoxOfficeRes>>

    @GET("titles/{titleId}")
    suspend fun getTitleDetails(@Path("titleId") titleId: String): Response<ApiResponse<TitleDetailsRes>>

}