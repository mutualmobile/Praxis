package com.mutualmobile.praxis.repo

import com.mutualmobile.praxis.NetworkResult
import com.mutualmobile.praxis.data.model.JokeListResponse
import com.mutualmobile.praxis.data.model.JokeResponse
import com.mutualmobile.praxis.data.services.ApiService

class JokeRepo constructor(private val apiService: ApiService) {

  suspend fun getFiveRandomJokes(): NetworkResult<JokeListResponse> {

    val response = apiService.getFiveRandomJokes()
        .await()
    if (response.isSuccessful) {
      val data = response.body()
      if (data != null) {
        return NetworkResult.Success(data)
      }
    }
    return NetworkResult.Failure(response)
  }

  suspend fun getRandomJoke(): NetworkResult<JokeResponse> {
    val response = apiService.getRandomJoke()
        .await()
    if (response.isSuccessful) {
      val data = response.body()
      if (data != null) {
        return NetworkResult.Success(data)
      }
    }
    return NetworkResult.Failure(response)
  }
}