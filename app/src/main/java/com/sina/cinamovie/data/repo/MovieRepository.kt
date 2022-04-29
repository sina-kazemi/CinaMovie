package com.sina.cinamovie.data.repo

import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.remote.MovieRemoteDataSource
import com.sina.cinamovie.data.res.TitleDetailsRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieRemoteDataSource: MovieRemoteDataSource) {

    suspend fun fetchMovie(itemId: String): Flow<Result<ApiResponse<TitleDetailsRes>>> {
        return flow {
            emit(movieRemoteDataSource.fetchMovie(itemId))
        }.flowOn(Dispatchers.IO)
    }

}