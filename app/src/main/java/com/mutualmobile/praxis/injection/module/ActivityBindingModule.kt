package com.mutualmobile.praxis.injection.module

import android.app.Activity
import com.mutualmobile.praxis.ui.joke.ShowJokeActivityComponent
import com.mutualmobile.praxis.ui.joke.ShowJokeActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class ActivityBindingModule {

//  @ActivityScope
//  @ContributesAndroidInjector(modules = arrayOf(ShowJokeActivityModule::class))
//  abstract fun showJokeActivity(): ShowJokeActivity

  @Binds
  @IntoMap
  @ActivityKey(ShowJokeActivity::class)
  internal abstract fun bindMainActivity(builder: ShowJokeActivityComponent.Builder): AndroidInjector.Factory<out Activity>

}