package com.mutualmobile.praxis.injection.component

import com.mutualmobile.praxis.BaseApplication
import com.mutualmobile.praxis.injection.module.ActivityBindingModule
import com.mutualmobile.praxis.injection.module.AppModule
import com.mutualmobile.praxis.injection.module.NetworkModule
import com.mutualmobile.praxis.injection.module.ViewModelFactoryModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, ViewModelFactoryModule::class, AndroidSupportInjectionModule::class, ActivityBindingModule::class,
    NetworkModule::class))
interface AppComponent : AndroidInjector<BaseApplication> {

  @Component.Builder abstract class Builder : AndroidInjector.Builder<BaseApplication>()
}
