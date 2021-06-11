package com.mutualmobile.praxis.data.sources

import com.mutualmobile.praxis.data.SafeResult
import com.mutualmobile.praxis.domain.responsemodels.JokeListResponse

/**
 * Created by Vipul Asri on 13/01/21.
 */

interface IJokesRemoteSource {
  suspend fun getFiveRandomJokes(): SafeResult<JokeListResponse>
}