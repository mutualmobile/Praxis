package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.data.repository.JokesRepo
import com.mutualmobile.praxis.data.sources.IJokesRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Rooparsh Kalia on 31/07/21
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