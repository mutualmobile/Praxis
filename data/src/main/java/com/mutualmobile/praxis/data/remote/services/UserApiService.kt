package com.mutualmobile.praxis.data.remote.services

import com.mutualmobile.praxis.data.remote.model.randomuser.DataLayer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface UserApiService {

  companion object {
    val baseUrl = "https://randomuser.me/"
    val RESULTS = "results"
  }

  @GET("/api")
  suspend fun getRandomUsers(@QueryMap queryMap: Map<String, Any>): Response<DataLayer.RandomUserResponse>
}