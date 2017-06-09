package com.mutualmobile.praxis.injection.module

import android.app.Activity
import android.app.FragmentManager
import android.support.v4.app.Fragment
import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.mutualmobile.praxis.injection.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module class FragmentModule(val fragment: Fragment) {
  @Provides @FragmentScope fun provideFragment(): Fragment {
    return fragment
  }

  @Provides @FragmentScope fun providesFragmentManager(@ActivityScope fragmentManager: FragmentManager): FragmentManager {
    return fragmentManager
  }

  @Provides @FragmentScope fun providesActivity(): Activity {
    return fragment.activity
  }
}