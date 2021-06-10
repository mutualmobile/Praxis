package com.mutualmobile.praxis.data.injection

import com.mutualmobile.praxis.data.repository.JokesRepo
import com.mutualmobile.praxis.data.usecases.GetFiveRandomJokesUseCase
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