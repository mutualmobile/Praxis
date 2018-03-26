package com.mutualmobile.praxis.ui.onboard

import dagger.Module
import dagger.Provides
import java.util.*

/**
 * Created by Harish on 26/03/18.
 *
 * OnBoardActivity specific module
 */
@Module
class OnBoardActivityModule {
  @Provides
  fun provideUUID() = UUID.randomUUID().toString()
}