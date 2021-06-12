package com.mutualmobile.praxis.data.repository

import com.mutualmobile.praxis.data.SafeResult
import com.mutualmobile.praxis.domain.responsemodels.JokeListResponse
import com.mutualmobile.praxis.data.sources.IJokesRemoteSource

/**
 * Created by Vipul Asri on 13/01/21.
 */

class JokesRepo(
  private val remoteSource: IJokesRemoteSource
) {

  suspend fun getFiveRandomJokes(): SafeResult<JokeListResponse> {
    return remoteSource.getFiveRandomJokes()
  }

}