package com.mutualmobile.praxis.data.remote.services

import com.mutualmobile.praxis.data.remote.model.NETJokeListData
import retrofit2.Retrofit
import retrofit2.http.GET

interface JokeApiService {
  @GET("/jokes/random/5")
  suspend fun getFiveRandomJokes(): NETJokeListData
}