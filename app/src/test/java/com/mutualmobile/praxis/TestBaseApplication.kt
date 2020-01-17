package com.mutualmobile.praxis

import com.mutualmobile.praxis.injection.component.DaggerTestAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TestBaseApplication : DaggerApplication(){
  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerTestAppComponent.builder().create(this)
  }


}
