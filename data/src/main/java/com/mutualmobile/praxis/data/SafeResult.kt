package com.mutualmobile.praxis.data

import com.mutualmobile.praxis.data.SafeResult.Failure
import com.mutualmobile.praxis.data.SafeResult.Success

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class SafeResult<out T> {

  data class Success<T>(val data: T) : SafeResult<T>()
  data class Failure(
    val exception: Exception? = Exception("Unknown Error"),
    val message: String = exception?.localizedMessage ?: ""
  ) : SafeResult<Nothing>()

  object NetworkError : SafeResult<Nothing>()

  override fun toString(): String {
    return when (this) {
      is Success -> "Success[data=$data]"
      is Failure -> "Failure[exception=$exception]"
      is NetworkError -> "NetworkError"
    }
  }
}

/**
 * `true` if [SafeResult] is of type [Success] & holds non-null [Success.data].
 */
val SafeResult<*>.succeeded
  get() = this is SafeResult.Success && data != null

fun <T> SafeResult<T>.getSuccessOrNull(): T? {
  return when (this) {
    is Success -> this.data
    else -> null
  }
}

fun <T> SafeResult<T>.getErrorOrNull(): Failure? {
  return when (this) {
    is Failure -> this
    else -> null
  }
}