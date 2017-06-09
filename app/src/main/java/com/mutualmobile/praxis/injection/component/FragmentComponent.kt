package com.mutualmobile.praxis.injection.component

import com.mutualmobile.praxis.injection.module.FragmentModule
import com.mutualmobile.praxis.injection.scope.FragmentScope
import dagger.Subcomponent

@FragmentScope @Subcomponent(modules = arrayOf(FragmentModule::class)) interface FragmentComponent
