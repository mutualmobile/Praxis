package com.mutualmobile.praxis.ui.joke

import android.content.Context
import com.mutualmobile.praxis.injection.qualifiers.ActivityContext
import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides

@Module
class ShowJokeActivityModule() {
//    var activity: Activity) {

  @Provides
  @ActivityContext internal fun provideActivityContext(activity: ShowJokeActivity): Context {
    return activity
  }

  @Provides
  @ActivityScope internal fun provideRxPermissions(activity: ShowJokeActivity): RxPermissions {
    return RxPermissions(activity)
  }

}
