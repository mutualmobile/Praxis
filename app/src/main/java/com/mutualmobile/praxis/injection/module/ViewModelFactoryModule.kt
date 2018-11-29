package com.mutualmobile.praxis.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mutualmobile.praxis.injection.scope.ViewModelScope
import com.mutualmobile.praxis.ui.home.about.AboutViewModel
import com.mutualmobile.praxis.ui.home.HomeViewModel
import com.mutualmobile.praxis.ui.joke.ShowJokeViewModel
import com.mutualmobile.praxis.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

  @Binds
  @IntoMap
  @ViewModelScope(ShowJokeViewModel::class)
  abstract fun bindShowJokeViewModel(showJokeViewModel: ShowJokeViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelScope(HomeViewModel::class)
  abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelScope(AboutViewModel::class)
  abstract fun bindAboutViewModel(aboutViewModel: AboutViewModel): ViewModel

}
