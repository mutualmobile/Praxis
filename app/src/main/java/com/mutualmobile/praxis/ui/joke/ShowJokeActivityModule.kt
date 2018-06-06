package com.mutualmobile.praxis.ui.joke

import android.content.Context
import com.mutualmobile.praxis.injection.module.BaseActivityModule
import com.mutualmobile.praxis.injection.qualifiers.ActivityContext
import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.mutualmobile.praxis.injection.scope.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity

@Module(includes = arrayOf(BaseActivityModule::class))
abstract class ShowJokeActivityModule {

  @Binds
  @ActivityContext abstract fun provideActivityContext(activity: ShowJokeActivity): Context

  @Binds
  @ActivityScope
  abstract fun provideActivity(showJokeActivity: ShowJokeActivity): DaggerAppCompatActivity
}
