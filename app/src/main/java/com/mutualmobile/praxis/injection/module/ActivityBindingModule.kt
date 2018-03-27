package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.mutualmobile.praxis.ui.joke.ShowJokeActivity
import com.mutualmobile.praxis.ui.joke.ShowJokeActivityModule
import com.mutualmobile.praxis.ui.onboard.OnBoardActivity
import com.mutualmobile.praxis.ui.onboard.OnBoardActivityModule
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity


@Module
abstract class ActivityBindingModule {

  @ActivityScope
  @ContributesAndroidInjector(
      modules = arrayOf(ShowJokeActivityModule::class))
  internal abstract fun bindShowJokeActivity(): ShowJokeActivity

  @ActivityScope
  @ContributesAndroidInjector(
      modules = arrayOf(OnBoardActivityModule::class))
  internal abstract fun bindOnBoardActivity(): OnBoardActivity
}

/**
 * Activity specific common dependencies should be placed here
 */
@Module
open class ActivityCommonModule {
  @ActivityScope
  @Provides internal fun provideRxPermissions(activity: DaggerAppCompatActivity) = RxPermissions(
      activity)
}