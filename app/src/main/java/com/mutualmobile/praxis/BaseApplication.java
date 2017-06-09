package com.mutualmobile.praxis;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.mutualmobile.praxis.injection.component.ApplicationComponent;
import com.mutualmobile.praxis.injection.component.DaggerApplicationComponent;
import com.mutualmobile.praxis.injection.module.AppModule;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class BaseApplication extends Application {

  private ApplicationComponent component;

  @Override public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    } else {
      Fabric.with(this, new Crashlytics());
    }
  }

  public ApplicationComponent getComponent() {
    if (component == null) {
      component = DaggerApplicationComponent.builder().appModule(new AppModule(this)).build();
    }
    return component;
  }
}

