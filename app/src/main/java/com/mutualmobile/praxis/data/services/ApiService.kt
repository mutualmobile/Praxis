package com.mutualmobile.praxis.data.services

import com.mutualmobile.praxis.data.model.JokeResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
  @GET("/jokes/random/") fun getRandomJoke(): Single<JokeResponse>
}