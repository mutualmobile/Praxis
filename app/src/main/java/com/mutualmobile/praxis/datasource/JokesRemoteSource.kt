package com.mutualmobile.praxis.datasource

import com.mutualmobile.praxis.data.SafeResult
import com.mutualmobile.praxis.data.remote.JokeApiService
import com.mutualmobile.praxis.domain.responsemodels.JokeListResponse
import com.mutualmobile.praxis.data.sources.IJokesRemoteSource
import com.mutualmobile.praxis.utils.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Vipul Asri on 13/01/21.
 */

class JokesRemoteSource(
  private val jokeApiService: JokeApiService,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IJokesRemoteSource {

  override suspend fun getFiveRandomJokes(): SafeResult<JokeListResponse> {
    return safeApiCall(dispatcher) {
      jokeApiService.getFiveRandomJokes()
    }
  }
}