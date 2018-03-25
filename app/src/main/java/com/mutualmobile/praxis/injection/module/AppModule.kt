package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.ui.joke.ShowJokeActivityComponent
import com.mutualmobile.praxis.utils.IRxSchedulers
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module(subcomponents = arrayOf(ShowJokeActivityComponent::class))
class AppModule {

  @Provides
  @Singleton internal fun provideRxSchedulers(): IRxSchedulers {
    return object : IRxSchedulers {
      override fun main() = AndroidSchedulers.mainThread()
      override fun io() = Schedulers.io()
    }
  }
}
