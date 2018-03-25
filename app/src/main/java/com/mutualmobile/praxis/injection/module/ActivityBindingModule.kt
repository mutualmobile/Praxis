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
import dagger.multibindings.IntoMap


@Module
abstract class ActivityBindingModule {

  @Binds
  @IntoMap
  @ActivityKey(ShowJokeActivity::class)
  internal abstract fun bindShowJokeActivity(builder: ShowJokeActivityComponent.Builder): AndroidInjector.Factory<out Activity>

  @ActivityScope
  @ContributesAndroidInjector(modules = arrayOf(OnBoardActivityModule::class))
  internal abstract fun bindOnBoardActivity(): OnBoardActivity

//  @Binds
//  @IntoMap
//  @ActivityKey(OnBoardActivity::class)
//  internal abstract fun bindOnBoardctivity(builder: OnBoardActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}