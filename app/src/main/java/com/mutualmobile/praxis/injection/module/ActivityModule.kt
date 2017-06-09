package com.mutualmobile.praxis.injection.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.mutualmobile.praxis.injection.qualifiers.ActivityContext
import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides

@Module class ActivityModule(val activity: AppCompatActivity) {

  @Provides @ActivityContext internal fun provideActivityContext(): Context {
    return activity
  }

  @Provides @ActivityScope internal fun provideRxPermissions(): RxPermissions {
    return RxPermissions(activity)
  }

}
