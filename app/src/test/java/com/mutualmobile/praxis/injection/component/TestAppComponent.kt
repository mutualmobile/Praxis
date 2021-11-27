package com.mutualmobile.praxis.injection.component

import com.mutualmobile.praxis.TestApplication
import com.mutualmobile.praxis.data.injection.RepositoryModule
import com.mutualmobile.praxis.data.injection.SourcesModule
import com.mutualmobile.praxis.domain.injection.UseCaseModule
import com.mutualmobile.praxis.injection.module.ActivityBindingModule
import com.mutualmobile.praxis.injection.module.FakeNetworkModule
import com.mutualmobile.praxis.injection.module.PreferenceModule
import com.mutualmobile.praxis.useCaseTest.GetFiveRandomJokesUseCaseTest
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
      AppModule::class,
      ViewModelFactoryModule::class,
      AndroidSupportInjectionModule::class,
      ActivityBindingModule::class,
      PreferenceModule::class,
      FakeNetworkModule::class,
      SourcesModule::class,
      RepositoryModule::class,
      UseCaseModule::class
    ]
)
interface TestAppComponent : AndroidInjector<TestApplication> {
  @Component.Factory
  interface Factory : AndroidInjector.Factory<TestApplication>

  fun inject(test: GetFiveRandomJokesUseCaseTest)

}