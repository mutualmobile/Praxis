package com.mutualmobile.praxis.injection.module

import android.app.Activity
import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.mutualmobile.praxis.ui.joke.ShowJokeActivity
import com.mutualmobile.praxis.ui.joke.ShowJokeActivityModule
import com.mutualmobile.praxis.ui.onboard.OnBoardActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Binds
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
      modules = arrayOf(ActivityModule::class))
  internal abstract fun bindOnBoardActivity(): OnBoardActivity

//  This is needed only when we use ActivityModule which is used when we don't Activity Specific module
  @Binds
  @ActivityScope
  internal abstract fun bindOBoardActivity(onBoardActivity: OnBoardActivity): DaggerAppCompatActivity
}

/**
 * Activity specific common dependencies should be placed here
 */
@Module
open class ActivityCommonModule {
  @ActivityScope
  @Provides internal fun provideRxPermissions(activity: Activity) = RxPermissions(
      activity)
}
