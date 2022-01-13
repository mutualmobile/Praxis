package com.mutualmobile.praxis.domain.usecases

import com.mutualmobile.praxis.domain.SafeResult
import com.mutualmobile.praxis.domain.model.Joke
import com.mutualmobile.praxis.domain.repository.IJokesRepo

/**
 * Created by Vipul Asri on 13/01/21.
 */

class GetFiveRandomJokesUseCase(private val jokesRepo: IJokesRepo) :
    BaseUseCase<SafeResult<List<Joke>>, Unit> {

  override suspend fun performAsync(): SafeResult<List<Joke>> {
    return when (val result = jokesRepo.getFiveRandomJokes()) {
      is SafeResult.Success -> SafeResult.Success(result.data.Jokes)
      is SafeResult.NetworkError -> result
      is SafeResult.Failure -> result
    }
  }

}