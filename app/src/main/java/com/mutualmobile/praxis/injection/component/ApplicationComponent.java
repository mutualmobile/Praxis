package com.mutualmobile.praxis.injection.component;

import com.mutualmobile.praxis.injection.module.ActivityModule;
import com.mutualmobile.praxis.injection.module.AppModule;
import com.mutualmobile.praxis.injection.module.NetworkModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { AppModule.class, NetworkModule.class }) public interface ApplicationComponent {

  ActivityComponent plusActivityComponent(ActivityModule activityModule);
}
