package com.sina.cinamovie.data.repo

import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.remote.SearchRemoteDataSource
import com.sina.cinamovie.data.res.GenresRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchRepository @Inject constructor(private val searchRemoteDataSource: SearchRemoteDataSource) {

    suspend fun fetchGenres(): Flow<Result<ApiResponse<List<GenresRes>>>> {
        return flow {
            emit(searchRemoteDataSource.fetchGenres())
        }.flowOn(Dispatchers.IO)
    }

}