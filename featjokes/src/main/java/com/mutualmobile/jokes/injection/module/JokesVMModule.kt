package com.mutualmobile.jokes.injection.module

import com.mutualmobile.jokes.model.JokeViewMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class JokesVMModule {

  @Provides
  fun provideUiModelMapper(): JokeViewMapper {
    return JokeViewMapper()
  }
}