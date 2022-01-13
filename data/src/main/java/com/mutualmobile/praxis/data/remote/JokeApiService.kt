package com.mutualmobile.praxis.data.remote

import com.mutualmobile.praxis.data.remote.model.JokeListEntity
import retrofit2.Retrofit
import retrofit2.http.GET

interface JokeApiService {

  companion object {
    fun createRetrofitService(retrofit: Retrofit): JokeApiService {
      return retrofit.create(JokeApiService::class.java)
    }

    private const val GET_RANDOM_JOKES = "/jokes/random/5"
  }

  @GET(GET_RANDOM_JOKES)
  suspend fun getFiveRandomJokes(): JokeListEntity
}