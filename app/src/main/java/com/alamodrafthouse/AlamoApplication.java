package com.alamodrafthouse;

import android.app.Application;
import com.alamodrafthouse.injection.component.ApplicationComponent;
import com.alamodrafthouse.injection.component.DaggerApplicationComponent;
import com.alamodrafthouse.injection.module.AppModule;

public class AlamoApplication extends Application {

  private ApplicationComponent component;

  public ApplicationComponent getComponent() {
    if (component == null) {
      component = DaggerApplicationComponent.builder().appModule(new AppModule(this)).build();
    }
    return component;
  }
}

