package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerAppCompatActivity

/**
 * Activity specific dependencies should be placed here
 */

@Module
class ActivityProviderModule {
  @ActivityScope
  @Provides internal fun provideRxPermissions(activity: DaggerAppCompatActivity) = RxPermissions(
      activity)
}