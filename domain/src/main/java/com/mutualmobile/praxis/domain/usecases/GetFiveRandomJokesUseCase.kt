package com.mutualmobile.praxis.domain.usecases

import com.mutualmobile.praxis.data.SafeResult
import com.mutualmobile.praxis.data.remote.model.JokeListResponse
import com.mutualmobile.praxis.data.repository.JokesRepo

/**
 * Created by Vipul Asri on 13/01/21.
 */

class GetFiveRandomJokesUseCase(private val jokesRepo: JokesRepo) :
    BaseUseCase<SafeResult<JokeListResponse>, Unit> {

  override suspend fun perform(): SafeResult<JokeListResponse> {
    return jokesRepo.getFiveRandomJokes()
  }

}