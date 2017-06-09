package com.mutualmobile.praxis.injection.component

import com.mutualmobile.praxis.injection.module.ActivityModule
import com.mutualmobile.praxis.injection.module.FragmentModule
import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.mutualmobile.praxis.ui.kotlinsample.KotlinActivity
import dagger.Subcomponent

@ActivityScope @Subcomponent(modules = arrayOf(ActivityModule::class)) interface ActivityComponent {

  fun plusFragmentComponent(fragmentModule: FragmentModule): FragmentComponent

  fun inject(kotlinActivity: KotlinActivity)
}
