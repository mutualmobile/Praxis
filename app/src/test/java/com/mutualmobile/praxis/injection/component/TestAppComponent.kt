package com.mutualmobile.praxis.injection.component

import android.content.Context
import com.mutualmobile.praxis.TestBaseApplication
import com.mutualmobile.praxis.injection.qualifiers.ApplicationContext
import com.mutualmobile.praxis.ui.joke.ShowJokeViewModelTest
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class])
interface TestAppComponent: AndroidInjector<TestBaseApplication>{

  fun inject(test : ShowJokeViewModelTest)

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<TestBaseApplication>() {
    @BindsInstance
    abstract fun appContext(@ApplicationContext context: Context)

    override fun seedInstance(instance: TestBaseApplication?) {
      appContext(instance!!.applicationContext)
    }
  }
}