package com.sina.cinamovie.data.repo

import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.remote.SearchRemoteDataSource
import com.sina.cinamovie.data.res.GenresRes
import com.sina.cinamovie.data.res.SearchTitlesRes
import com.sina.cinamovie.data.res.TitleDetailsRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class SearchRepository @Inject constructor(private val searchRemoteDataSource: SearchRemoteDataSource) {

    suspend fun fetchGenres(): Flow<Result<ApiResponse<List<GenresRes>>>> {
        return flow {
            emit(searchRemoteDataSource.fetchGenres())
        }.flowOn(Dispatchers.IO)
    }

    suspend fun advancedSearchTitle(genres: String = ""): Flow<Result<ApiResponse<List<SearchTitlesRes>>>> {
        return flow {
            Timber.d("RESPONSE:::::: ${searchRemoteDataSource.advancedSearchTitle(genres = genres)}")
            emit(searchRemoteDataSource.advancedSearchTitle(genres = genres))
        }.flowOn(Dispatchers.IO)
    }

}