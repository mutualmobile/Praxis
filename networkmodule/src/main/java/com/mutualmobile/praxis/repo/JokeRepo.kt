package com.mutualmobile.praxis.repo

import com.mutualmobile.praxis.NetworkResult
import com.mutualmobile.praxis.data.model.JokeListResponse
import com.mutualmobile.praxis.data.services.CoroutineApiService

class JokeRepo constructor(private val coroutineApiService: CoroutineApiService) {

  suspend fun getFiveRandomJokes(): NetworkResult<JokeListResponse> {
    val response = coroutineApiService.getFiveRandomJokes()
    if (response.isSuccessful) {
      val data = response.body()
      if (data != null) {
        return NetworkResult.Success(data)
      }
    }
    return NetworkResult.Failure(response)
  }
}