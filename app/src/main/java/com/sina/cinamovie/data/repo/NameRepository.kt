package com.sina.cinamovie.data.repo

import com.sina.cinamovie.data.ApiResponse
import com.sina.cinamovie.data.Result
import com.sina.cinamovie.data.remote.NameRemoteDataSource
import com.sina.cinamovie.data.res.NameDetailRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NameRepository @Inject constructor(var nameRemoteDataSource: NameRemoteDataSource) {

    suspend fun fetchName(itemId: String): Flow<Result<ApiResponse<NameDetailRes>>> {
        return flow {
            emit(nameRemoteDataSource.fetchName(itemId = itemId))
        }.flowOn(Dispatchers.IO)
    }

}