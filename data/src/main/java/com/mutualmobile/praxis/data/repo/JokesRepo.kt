package com.mutualmobile.praxis.data.repo

import com.mutualmobile.praxis.data.mapper.EntityMapper
import com.mutualmobile.praxis.data.remote.model.JokeListEntity
import com.mutualmobile.praxis.data.sources.IJokesRemoteSource
import com.mutualmobile.praxis.domain.SafeResult
import com.mutualmobile.praxis.domain.model.JokeList
import com.mutualmobile.praxis.domain.repository.IJokesRepo
import java.lang.RuntimeException

/**
 * Created by Vipul Asri on 13/01/21.
 */

class JokesRepo(
  private val remoteSource: IJokesRemoteSource,
  private val JokeListResponseMapper: EntityMapper<JokeList, JokeListEntity>
) : IJokesRepo {

  override suspend fun getFiveRandomJokes(): SafeResult<JokeList> {
    return when (val result = remoteSource.getFiveRandomJokes()) {
      is SafeResult.Success<*> -> SafeResult.Success(JokeListResponseMapper.mapToDomain(result.data as JokeListEntity))
      is SafeResult.Failure -> SafeResult.Failure(result.exception)
      SafeResult.NetworkError -> SafeResult.NetworkError
      else -> { throw RuntimeException()}
    }
  }

}