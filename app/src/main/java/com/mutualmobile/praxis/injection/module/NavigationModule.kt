package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.navigator.Navigator
import com.mutualmobile.praxis.navigator.PraxisNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

  @Provides
  @Singleton
  fun provideNavigator(): Navigator = PraxisNavigator()

}
