package com.mutualmobile.praxis.domain.injection

import com.mutualmobile.praxis.data.repository.JokesRepo
import com.mutualmobile.praxis.domain.usecases.GetFiveRandomJokesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vipul Asri on 13/01/21.
 */

@Module
object UseCaseModule {

  @Provides
  @Singleton
  @JvmStatic
  fun provideGetFiveRandomJokes(repo: JokesRepo): GetFiveRandomJokesUseCase {
    return GetFiveRandomJokesUseCase(repo)
  }

}