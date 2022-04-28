package com.sina.cinamovie.data

import com.sina.cinamovie.util.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber

suspend fun <T> getResponse(
    defaultErrorMessage: String = "There is an error" ,
    request: suspend () -> Response<T>
): Result<T> {
    return try {
        Timber.d("I'm working in thread ${Thread.currentThread().name}")
        val result = request.invoke()
        if (result.isSuccessful) {
            return Result.success(result.body())
        }
        else {
//            val errorResponse = ErrorUtils.parseError(result, retrofit)
            Result.error(defaultErrorMessage, null)
        }
    } catch (e: Throwable) {
        Timber.d("ErrorException :: ${e.message}")
        Result.error("Unknown Error", null)
    }
}