package com.mutualmobile.praxis.ui.onboard

import com.mutualmobile.praxis.injection.module.ActivityCommonModule
import com.mutualmobile.praxis.injection.scope.ActivityScope
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerAppCompatActivity
import java.util.*

/**
 * Created by Harish on 26/03/18.
 *
 * OnBoardActivity specific module
 */
@Module(includes = arrayOf(ActivityCommonModule::class))
class OnBoardActivityModule {
  @Provides
  fun provideUUID() = UUID.randomUUID().toString()

  @Provides
  @ActivityScope
  fun provideActivity(onBoardActivity: OnBoardActivity): DaggerAppCompatActivity = onBoardActivity
}