package com.mutualmobile.praxis.ui.joke

import com.mutualmobile.praxis.injection.module.ViewModelFactoryModule
import com.mutualmobile.praxis.injection.scope.ActivityScope
import dagger.Subcomponent
import dagger.android.AndroidInjector


@ActivityScope
@Subcomponent(modules = arrayOf(ViewModelFactoryModule::class, ShowJokeActivityModule::class))
interface ShowJokeActivityComponent : AndroidInjector<ShowJokeActivity> {

  @Subcomponent.Builder
  abstract class Builder : AndroidInjector.Builder<ShowJokeActivity>() {
    abstract override fun build(): ShowJokeActivityComponent
  }
}
