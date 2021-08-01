package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.AppConstants
import com.mutualmobile.praxis.data.remote.JokeApiService
import com.mutualmobile.praxis.data.remote.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Vipul Asri on 13/01/21.
 * Edited by Rooparsh Kalia on 30 Jul 2021
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return RetrofitHelper.createOkHttpClient()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return RetrofitHelper.createRetrofitClient(okHttpClient, AppConstants.BASE_URL)
    }

    @Provides
    @Singleton
    fun provideJokesApiService(retrofit: Retrofit): JokeApiService {
        return JokeApiService.createRetrofitService(retrofit)
    }

}