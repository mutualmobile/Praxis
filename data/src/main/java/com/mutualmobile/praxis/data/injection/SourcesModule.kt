package com.mutualmobile.praxis.data.injection

import com.mutualmobile.praxis.data.datasource.RandomUserRemoteDataSource
import com.mutualmobile.praxis.data.datasource.UserLocalDataSourceImpl
import com.mutualmobile.praxis.data.datasources.UserLocalDataSource
import com.mutualmobile.praxis.data.datasources.UserRemoteDataSource
import com.mutualmobile.praxis.data.injection.qualifiers.RandomUserClient
import com.mutualmobile.praxis.data.remote.services.GithubApiService
import com.mutualmobile.praxis.data.remote.services.JokeApiService
import com.mutualmobile.praxis.data.remote.services.UserApiService
import com.mutualmobile.praxis.data.sources.GithubReposRemoteSource
import com.mutualmobile.praxis.data.sources.IGithubReposRemoteSource
import com.mutualmobile.praxis.data.sources.IJokesRemoteSource
import com.mutualmobile.praxis.data.sources.JokesRemoteSource
import com.mutualmobile.praxis.injection.dispatcher.CoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Vipul Asri on 13/01/21.
 */
@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {

  @Provides
  @Singleton
  fun provideRandomUserRemoteDataSource(
    randomUserClient: UserApiService,
    coroutineDispatcherProvider: CoroutineDispatcherProvider
  ): UserRemoteDataSource =
    RandomUserRemoteDataSource(randomUserClient, coroutineDispatcherProvider)


  @Provides
  @Singleton
  fun provideRandomUserLocalDataSource(
  ): UserLocalDataSource =
    UserLocalDataSourceImpl()

  @Provides
  @Singleton
  fun provideJokesNetworkSource(apiService: JokeApiService): IJokesRemoteSource {
    return JokesRemoteSource(
      apiService
    )
  }

  @Provides
  @Singleton
  fun provideGithubNetworkSource(apiService: GithubApiService): IGithubReposRemoteSource {
    return GithubReposRemoteSource(
      apiService
    )
  }
}