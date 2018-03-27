package com.mutualmobile.praxis.ui.joke

import android.app.Activity
import android.content.Context
import com.mutualmobile.praxis.injection.module.ActivityCommonModule
import com.mutualmobile.praxis.injection.qualifiers.ActivityContext
import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.mutualmobile.praxis.injection.scope.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = arrayOf(ActivityCommonModule::class))
abstract class ShowJokeActivityModule {

  @FragmentScoped
  @ContributesAndroidInjector
  internal abstract fun ShowJokeFragment(): ShowJokeFragment

  @Binds
  @ActivityContext abstract fun provideActivityContext(activity: ShowJokeActivity): Context

  @Binds
  @ActivityScope
  abstract fun provideActivity(showJokeActivity: ShowJokeActivity): Activity
}

//Use the following approach if there is need to use non abstract function
//@Module(includes = arrayOf(ShowJokeActivityModule.Declarations::class, ActivityCommonModule::class))
//class ShowJokeActivityModule {
//
//  @Module
//  interface Declarations {
//    @ContributesAndroidInjector
//    fun ShowJokeFragment(): ShowJokeFragment
//  }
//
//  @Provides
//  @ActivityContext internal fun provideActivityContext(activity: ShowJokeActivity): Context {
//    return activity
//  }
//
//  @Provides
//  @ActivityScope
//  fun provideActivity(showJokeActivity: ShowJokeActivity) = showJokeActivity
//}
