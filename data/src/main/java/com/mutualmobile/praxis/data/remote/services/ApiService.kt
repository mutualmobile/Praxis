package com.mutualmobile.praxis.data.remote.services

import retrofit2.Retrofit

interface ApiService {
  companion object {
    inline fun <reified T> createRetrofitService(retrofit: Retrofit): T {
      return retrofit.create(T::class.java)
    }
  }
}