package com.sina.cinamovie.data.repo

import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.remote.HomeRemoteDataSource
import com.sina.cinamovie.data.res.HomeExtraRes
import com.sina.cinamovie.data.res.HomeRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class HomeRepository @Inject constructor(var homeRemoteDataSource: HomeRemoteDataSource){

    suspend fun fetchHome(): Flow<Result<ApiResponse<HomeRes>>> {
        return flow {
            emit(Result.loading())
            emit(homeRemoteDataSource.fetchHome())
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchHomeExtra(): Flow<Result<ApiResponse<HomeExtraRes>>> {
        return flow {
            emit(Result.loading())
            emit(homeRemoteDataSource.fetchHomeExtra())
        }.flowOn(Dispatchers.IO)
    }

}