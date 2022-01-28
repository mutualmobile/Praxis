package com.mutualmobile.di

import com.mutualmobile.praxis.data.injection.NetworkModule
import com.mutualmobile.praxis.data.remote.JokeApiService
import com.mutualmobile.praxis.data.remote.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class TestNetworkModule {

    @Provides
    @Singleton
    fun provideMockWebServer(): MockWebServer {
        var mockWebServer: MockWebServer? = null
        val thread = Thread {
            mockWebServer = MockWebServer()
            mockWebServer?.start()
        }
        thread.start()
        thread.join()
        return mockWebServer!!
    }

    @Provides
    @Singleton
    fun provideBaseUrl(mockWebServer: MockWebServer): String {
        return mockWebServer.url("/")
            .toString()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return RetrofitHelper.createOkHttpClient()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        rootUrl: String
    ): Retrofit {
        return RetrofitHelper.createRetrofitClient(okHttpClient, rootUrl)
    }

    @Provides
    @Singleton
    fun provideJokesApiService(retrofit: Retrofit): JokeApiService {
        return JokeApiService.createRetrofitService(retrofit)
    }

}