package com.mutualmobile.praxis.injection.module

import android.app.Activity
import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.mutualmobile.praxis.ui.joke.ShowJokeActivity
import com.mutualmobile.praxis.ui.joke.ShowJokeActivityComponent
import com.mutualmobile.praxis.ui.onboard.OnBoardActivity
import com.mutualmobile.praxis.ui.onboard.OnBoardActivityModule
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.multibindings.IntoMap


@Module
abstract class ActivityBindingModule {

  @Binds
  @IntoMap
  @ActivityKey(ShowJokeActivity::class)
  internal abstract fun bindShowJokeActivity(
      builder: ShowJokeActivityComponent.Builder): AndroidInjector.Factory<out Activity>

  @ActivityScope
  @ContributesAndroidInjector(
      modules = arrayOf(BaseOnBoardActivityModule::class, OnBoardActivityModule::class))
  internal abstract fun bindOnBoardActivity(): OnBoardActivity
}


/**
 * Activities Common Modules
 * Should be declared for each activity
 *
 * Activity Specified should be created separately
 */
@Module internal abstract class BaseOnBoardActivityModule : BaseActivityBindsModule<OnBoardActivity>()


/**
 * Base Activity Binds Module
 *
 * Activity specific dependencies should be placed in ActivityProviderModule.kt
 */
@Module(includes = arrayOf(ActivityProviderModule::class))
abstract internal class BaseActivityBindsModule<in T : DaggerAppCompatActivity> {
  @Binds
  @ActivityScope
  protected abstract fun providesAppCompatActivity(t: T): DaggerAppCompatActivity
}