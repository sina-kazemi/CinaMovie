package com.sina.cinamovie.data.remote

import com.sina.cinamovie.api.ApiService
import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.getResponse
import com.sina.cinamovie.data.res.ChartBoxOfficeRes
import com.sina.cinamovie.data.res.HomeRes
import javax.inject.Inject

class ChartRemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun fetchBoxOffice(): Result<ApiResponse<ChartBoxOfficeRes>> = getResponse { apiService.getChartBoxOffice() }

}