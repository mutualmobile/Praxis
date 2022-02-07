package com.mutualmobile.praxis.data.datasource

import com.mutualmobile.praxis.data.remote.safeApiCall
import com.mutualmobile.praxis.data.remote.services.UserApiService
import com.mutualmobile.praxis.domain.SafeResult
import com.mutualmobile.praxis.data.datasources.UserRemoteDataSource
import com.mutualmobile.praxis.data.remote.model.randomuser.DataLayer.RandomUserResponse
import com.mutualmobile.praxis.injection.dispatcher.CoroutineDispatcherProvider
import retrofit2.Response

class RandomUserRemoteDataSource(
  private val randomUserApiService: UserApiService,
  private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
) :
  UserRemoteDataSource {
  override suspend fun fetchUsers(queryMap: Map<String, Int?>): SafeResult<Response<RandomUserResponse>> {
    val response = safeApiCall(coroutineDispatcherProvider.io) {
      randomUserApiService.getRandomUsers(queryMap)
    }
    return response
  }
}