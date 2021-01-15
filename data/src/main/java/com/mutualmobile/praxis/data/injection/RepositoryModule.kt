package com.mutualmobile.praxis.data.injection

import com.mutualmobile.praxis.data.repository.JokesRepository
import com.mutualmobile.praxis.data.sources.IJokesRemoteSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vipul Asri on 13/01/21.
 */

@Module
object RepositoryModule {

  @Provides
  @Singleton
  @JvmStatic
  fun provideJokesRepository(networkSource: IJokesRemoteSource): JokesRepository {
    return JokesRepository(networkSource)
  }

}