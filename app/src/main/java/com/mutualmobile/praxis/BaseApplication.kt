package com.mutualmobile.praxis

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.mutualmobile.praxis.injection.component.ApplicationComponent
import com.mutualmobile.praxis.injection.component.DaggerApplicationComponent
import com.mutualmobile.praxis.injection.module.AppModule
import io.fabric.sdk.android.Fabric
import timber.log.Timber

class BaseApplication : Application() {

  private var component: ApplicationComponent? = null

  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    } else {
      Fabric.with(this, Crashlytics())
    }
  }

  fun getComponent(): ApplicationComponent {
    if (component == null) {
      component = DaggerApplicationComponent.builder().appModule(AppModule(this)).build()
    }
    return component!!
  }
}

