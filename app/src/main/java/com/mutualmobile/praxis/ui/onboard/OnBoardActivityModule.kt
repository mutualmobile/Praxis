package com.mutualmobile.praxis.ui.onboard

import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides

@Module
class OnBoardActivityModule {
  @Provides
  @ActivityScope
  internal fun provideRxPermissions(activity: OnBoardActivity): RxPermissions {
    return RxPermissions(activity)
  }
}