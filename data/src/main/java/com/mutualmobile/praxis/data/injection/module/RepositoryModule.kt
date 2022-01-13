package com.mutualmobile.praxis.data.injection.module

import com.mutualmobile.praxis.data.remote.model.JokesListResponseMapper
import com.mutualmobile.praxis.data.repo.JokesRepo
import com.mutualmobile.praxis.data.sources.IJokesRemoteSource
import com.mutualmobile.praxis.domain.repository.IJokesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Vipul Asri on 13/01/21.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

  @Provides
  fun provideJokesRepository(networkSource: IJokesRemoteSource): IJokesRepo {
    return JokesRepo(networkSource, JokesListResponseMapper())
  }

}