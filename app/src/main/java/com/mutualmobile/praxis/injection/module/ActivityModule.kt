package com.mutualmobile.praxis.injection.module

import android.app.Activity
import com.mutualmobile.praxis.injection.scope.ActivityScope
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerAppCompatActivity
import java.util.*

@Module
(includes = arrayOf(ActivityCommonModule::class))
class ActivityModule {
  @Provides
  fun provideUUID() = UUID.randomUUID().toString()

  @Provides
  @ActivityScope
  fun provideActivity(activity: DaggerAppCompatActivity):
      Activity = activity
}