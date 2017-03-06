package com.alamodrafthouse.injection.component;

import com.alamodrafthouse.injection.module.ActivityModule;
import com.alamodrafthouse.injection.scope.ActivityScope;
import com.alamodrafthouse.ui.category.CategoryActivity;
import dagger.Component;

/**
 * Created by Shekar on 3/3/17.
 */

@ActivityScope @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class
}) public interface ActivityComponent {
  void inject(CategoryActivity categoryActivity);
}
