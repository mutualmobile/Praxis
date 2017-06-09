package com.mutualmobile.praxis.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import com.mutualmobile.praxis.BR;
import com.mutualmobile.praxis.BaseApplication;
import com.mutualmobile.praxis.injection.component.ActivityComponent;
import com.mutualmobile.praxis.injection.module.ActivityModule;
import javax.inject.Inject;

public abstract class BaseActivity<B extends ViewDataBinding, V extends MvvmView, VM extends MvvmViewModel> extends AppCompatActivity {
  protected ActivityComponent component;

  @Inject protected VM viewModel;

  protected B binding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Inject dependencies
    onComponentCreated(getComponent());
    // Bind the view and bind the viewModel to layout
    bindContentView(layoutId());
  }

  public void bindContentView(int layoutId) {
    binding = DataBindingUtil.setContentView(this, layoutId);
    viewModel.attachView((MvvmView) this);
    binding.setVariable(BR.viewModel, viewModel);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    viewModel.detachView();
  }

  public ActivityComponent getComponent() {
    if (component == null) {
      component = ((BaseApplication) getApplication()).getComponent().plusActivityComponent(new ActivityModule(this));
    }
    return component;
  }

  @LayoutRes protected abstract int layoutId();

  protected abstract void onComponentCreated(ActivityComponent component);
}
