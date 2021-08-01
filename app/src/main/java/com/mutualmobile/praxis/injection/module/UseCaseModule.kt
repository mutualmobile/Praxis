package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.data.repository.JokesRepo
import com.mutualmobile.praxis.data.sources.IJokesRemoteSource
import com.mutualmobile.praxis.data.usecases.GetFiveRandomJokesUseCase
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
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetFiveRandomJokes(repo: JokesRepo): GetFiveRandomJokesUseCase {
        return GetFiveRandomJokesUseCase(repo)
    }

}