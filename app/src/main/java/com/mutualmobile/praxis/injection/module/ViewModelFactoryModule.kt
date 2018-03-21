package com.mutualmobile.praxis.injection.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.mutualmobile.praxis.injection.scope.ViewModelScope
import com.mutualmobile.praxis.ui.kotlinsample.KotlinViewModel
import com.mutualmobile.praxis.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

  @Binds
  @IntoMap
  @ViewModelScope(KotlinViewModel::class)
  abstract fun bindViewModel(kotlinViewModel: KotlinViewModel): ViewModel

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
