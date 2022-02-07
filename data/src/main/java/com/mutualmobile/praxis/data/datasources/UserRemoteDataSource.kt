package com.mutualmobile.praxis.data.datasources

import com.mutualmobile.praxis.data.remote.model.randomuser.DataLayer
import com.mutualmobile.praxis.domain.SafeResult
import retrofit2.Response

interface UserRemoteDataSource {
  suspend fun fetchUsers(queryMap: Map<String, Int?>): SafeResult<Response<DataLayer.RandomUserResponse>>
}
