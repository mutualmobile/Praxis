package com.mutualmobile.praxis.data.services

import com.mutualmobile.praxis.data.model.JokeListResponse
import retrofit2.Response
import retrofit2.http.GET

interface CoroutineApiService {
  @GET("/jokes/random/5") suspend fun getFiveRandomJokes(): Response<JokeListResponse>
}