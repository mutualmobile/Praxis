package com.mutualmobile.praxis.data.remote

import com.mutualmobile.praxis.domain.SafeResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException

internal suspend fun <T> safeApiCall(
  dispatcher: CoroutineDispatcher,
  apiCall: suspend () -> T
): SafeResult<T> {
  return withContext(dispatcher) {
    try {
      SafeResult.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
      Timber.e(throwable.message.toString())
      when (throwable) {
        is IOException -> SafeResult.NetworkError
        else -> SafeResult.Failure(Exception(throwable))
      }
    }
  }
}
