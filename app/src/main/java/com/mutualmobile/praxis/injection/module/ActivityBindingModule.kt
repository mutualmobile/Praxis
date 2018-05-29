package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.mutualmobile.praxis.ui.home.HomeActivity
import com.mutualmobile.praxis.ui.home.HomeViewModel
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
      modules = arrayOf(OnBoardActivityModule::class))
  internal abstract fun bindOnBoardActivity(): OnBoardActivity

  @ActivityScope
  @ContributesAndroidInjector(
      modules = arrayOf(HomeActivityModule::class))
  internal abstract fun bindHomeActivity(): HomeActivity

}

@Module internal abstract class OnBoardActivityModule : ActivityModule<OnBoardActivity>()
@Module internal abstract class HomeActivityModule : ActivityModule<HomeActivity>()


@Module(includes = arrayOf(BaseActivityModule::class))
abstract class ActivityModule<in T : DaggerAppCompatActivity> {
  @Binds
  @ActivityScope
  internal abstract fun bindActivity(activity: T): DaggerAppCompatActivity
}

/**
 * Activity specific common dependencies should be placed here
 */
@Module
open class BaseActivityModule {
  @ActivityScope
  @Provides internal fun provideRxPermissions(activity: DaggerAppCompatActivity) = RxPermissions(
      activity)
}