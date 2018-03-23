package com.mutualmobile.praxis.injection.module

import android.app.Activity
import com.mutualmobile.praxis.ui.joke.ShowJokeActivity
import com.mutualmobile.praxis.ui.joke.ShowJokeActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class ActivityBindingModule {

  @Binds
  @IntoMap
  @ActivityKey(ShowJokeActivity::class)
  internal abstract fun bindShowJokeActivity(builder: ShowJokeActivityComponent.Builder): AndroidInjector.Factory<out Activity>

}