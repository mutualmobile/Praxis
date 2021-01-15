package com.mutualmobile.praxis.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mutualmobile.praxis.injection.scope.ViewModelScope
import com.mutualmobile.praxis.ui.home.about.AboutVM
import com.mutualmobile.praxis.ui.home.HomeVM
import com.mutualmobile.praxis.ui.joke.ShowJokeVM
import com.mutualmobile.praxis.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelScope(ShowJokeVM::class)
  abstract fun bindShowJokeVM(showJokeVM: ShowJokeVM): ViewModel

  @Binds
  @IntoMap
  @ViewModelScope(HomeVM::class)
  abstract fun bindHomeVM(homeVM: HomeVM): ViewModel

  @Binds
  @IntoMap
  @ViewModelScope(AboutVM::class)
  abstract fun bindAboutVM(aboutViewModel: AboutVM): ViewModel

}
