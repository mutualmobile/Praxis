package com.mutualmobile.praxis.utils

import android.util.Log
import com.mutualmobile.praxis.data.SafeResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

internal suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
): SafeResult<T> {
    return withContext(dispatcher) {
        try {
            SafeResult.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            Log.e("safeApiCall", throwable.message.toString())
            when (throwable) {
                is IOException -> SafeResult.NetworkError
                is HttpException -> SafeResult.Failure(throwable)
                else -> SafeResult.Failure(Exception(throwable))
            }
        }
    }
}
