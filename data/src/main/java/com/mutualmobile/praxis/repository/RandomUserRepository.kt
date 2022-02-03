package com.mutualmobile.praxis.repository

import com.mutualmobile.praxis.data.remote.services.UserApiService
import com.mutualmobile.praxis.domain.SafeResult
import com.mutualmobile.praxis.data.datasources.UserLocalDataSource
import com.mutualmobile.praxis.data.datasources.UserRemoteDataSource
import com.mutualmobile.praxis.data.mapper.EntityMapper
import com.mutualmobile.praxis.data.remote.model.randomuser.DataLayer
import com.mutualmobile.praxis.domain.exceptions.NetworkErrorException
import com.mutualmobile.praxis.domain.exceptions.UnknownException
import com.mutualmobile.praxis.domain.model.randomuser.DomainLayer
import com.mutualmobile.praxis.repository.exceptions.NoUsersAvailableException
import javax.inject.Inject

class RandomUserRepository @Inject constructor(
  private val userRemoteDataSource: UserRemoteDataSource,
  private val userLocalDataSource: UserLocalDataSource,
  private val mapper: EntityMapper<DomainLayer.RandomUserResponse, DataLayer.RandomUserResponse>

) : UserRepository {
  override suspend fun fetchUsers(numOfResults: Int): DomainLayer.RandomUserResponse {
    val result = userRemoteDataSource.fetchUsers(hashMapOf<String, Any>().apply {
      UserApiService.RESULTS to 50
    })
    when (result) {
      is SafeResult.Failure -> throw result.exception ?: UnknownException()
      SafeResult.NetworkError -> throw NetworkErrorException()
      is SafeResult.Success -> {
        result.data.body()?.let {
          return mapper.mapToDomain(it)
        } ?: run {
          throw NoUsersAvailableException()
        }
      }
    }
  }

  override suspend fun saveUsers(users: List<DomainLayer.RandomUser>) {
    userLocalDataSource.saveUsers(users)
  }
}