package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.ui.model.UIJokeMapper
import com.mutualmobile.praxis.ui.model.UIRepoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

  @Provides
  fun provideUiModelMapper(): UIJokeMapper {
    return UIJokeMapper()
  }

  @Provides
  fun provideUiRepoMapper(): UIRepoMapper {
    return UIRepoMapper()
  }
}