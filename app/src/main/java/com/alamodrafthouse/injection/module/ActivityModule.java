package com.alamodrafthouse.injection.module;

import com.alamodrafthouse.injection.scope.ActivityScope;
import com.alamodrafthouse.ui.category.CategoryAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Sekhar on 4/8/15.
 */
@Module public class ActivityModule {

  @Provides @ActivityScope public CategoryAdapter provideCategoryAdapter() {
    return new CategoryAdapter();
  }
}
