package com.mutualmobile.praxis.repo

import com.mutualmobile.praxis.NetworkResult
import com.mutualmobile.praxis.data.model.JokeListResponse
import com.mutualmobile.praxis.data.services.CoroutineApiService
import com.mutualmobile.praxis.data.services.RxApiService
import io.reactivex.Single

class JokeRepo constructor(
    private val coroutineApiService: CoroutineApiService,
    private val rxApiService: RxApiService) {

  suspend fun getFiveRandomJokesCoroutine(): NetworkResult<JokeListResponse> {
    val response = coroutineApiService.getFiveRandomJokes()
    if (response.isSuccessful) {
      val data = response.body()
      if (data != null) {
        return NetworkResult.Success(data)
      }
    }
    return NetworkResult.Failure(response)
  }
  fun getFiveRandomJokesRx(): Single<JokeListResponse> {

    return rxApiService.getFiveRandomJokes()
  }
}