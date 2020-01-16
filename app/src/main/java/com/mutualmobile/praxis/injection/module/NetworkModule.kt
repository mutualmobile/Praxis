package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.AppConstants
import com.mutualmobile.praxis.BuildConfig
import com.mutualmobile.praxis.data.services.CoroutineApiService
import com.mutualmobile.praxis.data.services.RxApiService
import com.mutualmobile.praxis.repo.JokeRepo
import com.mutualmobile.praxis.utils.IRxSchedulers
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides @Singleton internal fun provideOkHttpClient(): OkHttpClient {
    val httpBuilder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
      val httpLoggingInterceptor = HttpLoggingInterceptor()
      httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
      httpBuilder.interceptors()
          .add(httpLoggingInterceptor)
    }
    return httpBuilder.build()
  }

  @Provides @Singleton @Named(AppConstants.COROUTINE_RETROFIT) internal fun provideCoroutineRestAdapter(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
  }

  @Provides @Singleton internal fun provideCoroutineApiService( @Named(AppConstants.COROUTINE_RETROFIT) restAdapter: Retrofit): CoroutineApiService {
    return restAdapter.create(CoroutineApiService::class.java)
  }

  @Provides @Singleton internal fun provideJokeRepo(coroutineApiService: CoroutineApiService, rxApiService: RxApiService, schedulers: IRxSchedulers): JokeRepo {
    return JokeRepo(coroutineApiService, rxApiService, schedulers)
  }

  @Provides @Singleton @Named(AppConstants.RX_RETROFIT) internal fun provideRxRestAdapter(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
  }

  @Provides @Singleton internal fun provideRxApiService( @Named(AppConstants.RX_RETROFIT) restAdapter: Retrofit): RxApiService {
    return restAdapter.create(RxApiService::class.java)
  }
}
