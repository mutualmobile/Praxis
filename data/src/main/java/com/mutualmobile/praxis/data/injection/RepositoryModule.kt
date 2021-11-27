package com.mutualmobile.praxis.data.injection

import com.mutualmobile.praxis.data.repository.JokesRepo
import com.mutualmobile.praxis.data.sources.IJokesRemoteSource
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
object RepositoryModule {

  @Provides
  @Singleton
  fun provideJokesRepository(networkSource: IJokesRemoteSource): JokesRepo {
    return JokesRepo(networkSource)
  }

}