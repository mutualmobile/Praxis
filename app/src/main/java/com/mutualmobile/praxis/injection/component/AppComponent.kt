package com.mutualmobile.praxis.injection.component

import android.content.Context
import com.mutualmobile.praxis.BaseApplication
import com.mutualmobile.praxis.injection.module.ActivityBindingModule
import com.mutualmobile.praxis.injection.module.AppModule
import com.mutualmobile.praxis.injection.module.NetworkModule
import com.mutualmobile.praxis.injection.module.PreferenceModule
import com.mutualmobile.praxis.injection.module.ViewModelFactoryModule
import com.mutualmobile.praxis.injection.qualifiers.ApplicationContext
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelFactoryModule::class, AndroidSupportInjectionModule::class, ActivityBindingModule::class, NetworkModule::class, PreferenceModule::class])
interface AppComponent : AndroidInjector<BaseApplication> {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<BaseApplication>() {
    @BindsInstance
    abstract fun appContext(@ApplicationContext context: Context)

    override fun seedInstance(instance: BaseApplication?) {
      appContext(instance!!.applicationContext)
    }
  }
}
