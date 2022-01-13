package com.mutualmobile.praxis.data.sources

import com.mutualmobile.praxis.data.remote.JokeApiService
import com.mutualmobile.praxis.data.remote.model.JokeListEntity
import com.mutualmobile.praxis.data.remote.safeApiCall
import com.mutualmobile.praxis.domain.SafeResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Vipul Asri on 13/01/21.
 */

class JokesRemoteSource(
  private val jokeApiService: JokeApiService,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IJokesRemoteSource {

  override suspend fun getFiveRandomJokes(): SafeResult<JokeListEntity> {
    return safeApiCall(dispatcher) {
      jokeApiService.getFiveRandomJokes()
    }
  }
}