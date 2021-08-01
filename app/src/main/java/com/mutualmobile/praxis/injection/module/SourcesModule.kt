package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.data.remote.JokeApiService
import com.mutualmobile.praxis.data.sources.IJokesRemoteSource
import com.mutualmobile.praxis.datasource.JokesRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Vipul Asri on 13/01/21.
 * Edited by Rooparsh Kalia on 30 Jul 2021
 */

@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {

  @Provides
  @Singleton
  fun provideJokesNetworkSource(apiService: JokeApiService): IJokesRemoteSource {
    return JokesRemoteSource(
      apiService
    )
  }

}