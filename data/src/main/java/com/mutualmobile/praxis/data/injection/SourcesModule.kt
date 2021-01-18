package com.mutualmobile.praxis.data.injection

import com.mutualmobile.praxis.data.remote.JokeApiService
import com.mutualmobile.praxis.data.sources.IJokesRemoteSource
import com.mutualmobile.praxis.data.sources.JokesRemoteSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vipul Asri on 13/01/21.
 */

@Module
object SourcesModule {

  @Provides
  @Singleton
  @JvmStatic
  fun provideJokesNetworkSource(apiService: JokeApiService): IJokesRemoteSource {
    return JokesRemoteSource(
        apiService
    )
  }

}