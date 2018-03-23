package com.mutualmobile.praxis

import com.crashlytics.android.Crashlytics
import com.mutualmobile.praxis.injection.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.fabric.sdk.android.Fabric
import timber.log.Timber

class BaseApplication : DaggerApplication() {
  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder().create(this)
  }

  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    } else {
      Fabric.with(this, Crashlytics())
    }
  }

}
