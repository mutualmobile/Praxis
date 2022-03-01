package com.mutualmobile.praxis.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) class AppModule {

  @Provides
  @Singleton
  fun provideAppContext(@ApplicationContext application: Context): Context {
    return application
  }
}
