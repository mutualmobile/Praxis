package com.alamodrafthouse.injection.component;

import com.alamodrafthouse.data.DataManager;
import com.alamodrafthouse.injection.module.AppModule;
import com.alamodrafthouse.injection.module.NetworkModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by Shekar on 3/3/17.
 */

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
})
public interface ApplicationComponent {
    DataManager getDataManager();
}
