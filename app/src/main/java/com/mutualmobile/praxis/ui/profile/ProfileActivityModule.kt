package com.mutualmobile.praxis.ui.profile

import android.content.Context
import com.mutualmobile.praxis.injection.module.BaseActivityModule
import com.mutualmobile.praxis.injection.qualifiers.ActivityContext
import com.mutualmobile.praxis.injection.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.android.support.DaggerAppCompatActivity

@Module(includes = [BaseActivityModule::class])
abstract class ProfileActivityModule {

  @Binds
  @ActivityContext abstract fun provideActivityContext(activity: ProfileActivity): Context

  @Binds
  @ActivityScope
  abstract fun provideActivity(showJokeActivity: ProfileActivity): DaggerAppCompatActivity
}
