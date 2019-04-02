package com.mutualmobile.praxis.data.services

import com.mutualmobile.praxis.data.model.JokeListResponse
import com.mutualmobile.praxis.data.model.JokeResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
  @GET("/jokes/random/") fun getRandomJoke(): Deferred<Response<JokeResponse>>
  @GET("/jokes/random/5") fun getFiveRandomJokes(): Deferred<Response<JokeListResponse>>
}