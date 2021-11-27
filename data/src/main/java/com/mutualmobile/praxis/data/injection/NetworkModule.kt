package com.mutualmobile.praxis.data.injection

import com.mutualmobile.praxis.data.AppConstants
import com.mutualmobile.praxis.data.remote.JokeApiService
import com.mutualmobile.praxis.data.remote.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
/**
 * Created by Vipul Asri on 13/01/21.
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  fun provideHttpClient(): OkHttpClient {
    return RetrofitHelper.createOkHttpClient()
  }

  @Provides
  fun provideRetrofit(
    okHttpClient: OkHttpClient
  ): Retrofit {
    return RetrofitHelper.createRetrofitClient(okHttpClient, AppConstants.BASE_URL)
  }

  @Provides
  fun provideJokesApiService(retrofit: Retrofit): JokeApiService {
    return JokeApiService.createRetrofitService(retrofit)
  }

}