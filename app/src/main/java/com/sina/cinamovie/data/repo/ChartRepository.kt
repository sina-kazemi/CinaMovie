package com.sina.cinamovie.data.repo

import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.remote.ChartRemoteDataSource
import com.sina.cinamovie.data.res.ChartBoxOfficeRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ChartRepository @Inject constructor(private val chartRemoteDataSource: ChartRemoteDataSource) {

    suspend fun fetchBoxOffice(): Flow<Result<ApiResponse<ChartBoxOfficeRes>>> {
        return flow {
            emit(Result.loading())
            emit(chartRemoteDataSource.fetchBoxOffice())
        }.flowOn(Dispatchers.IO)
    }

}