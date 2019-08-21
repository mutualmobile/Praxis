package com.mutualmobile.praxis.repo

import com.mutualmobile.praxis.NetworkResult
import com.mutualmobile.praxis.data.model.JokeListResponse
import com.mutualmobile.praxis.data.model.JokeResponse
import com.mutualmobile.praxis.data.services.CoroutineApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JokeRepo constructor(private val coroutineApiService: CoroutineApiService) {

  suspend fun getFiveRandomJokes(): NetworkResult<JokeListResponse> {
    return withContext(Dispatchers.IO) {
      val response = coroutineApiService.getFiveRandomJokes()
      if (response.isSuccessful) {
        val data = response.body()
        if (data != null) {
          return@withContext NetworkResult.Success(data)
        }
      }
      NetworkResult.Failure(response)
    }
  }

  suspend fun getRandomJoke(): NetworkResult<JokeResponse> {
    val response = coroutineApiService.getRandomJoke()
    if (response.isSuccessful) {
      val data = response.body()
      if (data != null) {
        return NetworkResult.Success(data)
      }
    }
    return NetworkResult.Failure(response)
  }
}