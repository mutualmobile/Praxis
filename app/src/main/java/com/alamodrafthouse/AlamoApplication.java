package com.alamodrafthouse;

import android.app.Application;
import com.alamodrafthouse.injection.component.ApplicationComponent;
import com.alamodrafthouse.injection.component.DaggerApplicationComponent;
import com.alamodrafthouse.injection.module.AppModule;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class AlamoApplication extends Application {

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

