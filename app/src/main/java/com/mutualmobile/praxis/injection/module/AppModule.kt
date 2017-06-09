package com.mutualmobile.praxis.injection.module

import android.app.Application
import android.content.Context
import com.mutualmobile.praxis.injection.qualifiers.ApplicationContext
import com.mutualmobile.praxis.utils.IRxSchedulers
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module class AppModule(private val application: Application) {

  @Provides internal fun provideApplication(): Application {
    return application
  }

  @Provides @ApplicationContext internal fun provideContext(): Context {
    return application
  }

  @Provides @Singleton internal fun provideRxSchedulers(): IRxSchedulers {
    return object : IRxSchedulers {
      override fun main() = AndroidSchedulers.mainThread()
      override fun io() = Schedulers.io()
    }
  }
}
