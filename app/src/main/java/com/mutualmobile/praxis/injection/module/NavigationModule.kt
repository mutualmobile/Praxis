package com.mutualmobile.praxis.injection.module

import android.app.Activity
import android.content.Context
import com.mutualmobile.praxis.navigator.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class NavigationModule {

  @Provides
  @ActivityScoped
  fun provideNavigator(@ActivityContext context: Context): Navigator = Navigator(context)

}
