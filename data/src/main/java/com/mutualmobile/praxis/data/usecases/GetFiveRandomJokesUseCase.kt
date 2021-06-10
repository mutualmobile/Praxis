package com.mutualmobile.praxis.data.usecases

import com.mutualmobile.praxis.data.SafeResult
import com.mutualmobile.praxis.data.SafeResult.Failure
import com.mutualmobile.praxis.data.SafeResult.NetworkError
import com.mutualmobile.praxis.data.SafeResult.Success
import com.mutualmobile.praxis.data.repository.JokesRepo
import com.mutualmobile.praxis.domain.mappers.toJokes
import com.mutualmobile.praxis.domain.model.Joke

/**
 * Created by Vipul Asri on 13/01/21.
 */

class GetFiveRandomJokesUseCase(private val jokesRepo: JokesRepo) :
    BaseUseCase<SafeResult<List<Joke>>, Unit> {

  override suspend fun perform(): SafeResult<List<Joke>> {
    return when (val result = jokesRepo.getFiveRandomJokes()) {
      is Success -> Success(result.data.toJokes())
      is NetworkError -> result
      is Failure -> result
    }
  }

}