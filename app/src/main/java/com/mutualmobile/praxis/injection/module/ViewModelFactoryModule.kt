package com.mutualmobile.praxis.injection.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.mutualmobile.praxis.injection.scope.ViewModelScope
import com.mutualmobile.praxis.ui.joke.ShowJokeViewModel
import com.mutualmobile.praxis.ui.onboard.OnBoardViewModel
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
  @ViewModelScope(OnBoardViewModel::class)
  abstract fun bindOnBoardViewModel(onBoardViewModel: OnBoardViewModel): ViewModel


  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
