package com.alamodrafthouse.injection.module;

import com.alamodrafthouse.AlamoApplication;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Shekar on 3/3/17.
 */

@Module
public class AppModule {
    AlamoApplication application;

    public AppModule(AlamoApplication app) {
        application = app;
    }

    @Provides AlamoApplication provideBaseApplication() {
        return application;
    }
}
