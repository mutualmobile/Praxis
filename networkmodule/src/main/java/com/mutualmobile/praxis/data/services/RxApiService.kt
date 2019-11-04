package com.mutualmobile.praxis.data.services

import com.mutualmobile.praxis.data.model.JokeListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface RxApiService {
  @GET("/jokes/random/5") fun getFiveRandomJokes(): Single<JokeListResponse>
}