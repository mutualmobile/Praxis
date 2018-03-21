package com.mutualmobile.praxis.ui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.mutualmobile.praxis.BR
import com.mutualmobile.praxis.BaseApplication
import com.mutualmobile.praxis.injection.component.ActivityComponent
import com.mutualmobile.praxis.injection.module.ActivityModule
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {
  private var component: ActivityComponent? = null
  protected lateinit var binding: B
  lateinit var viewModel: VM

  @Inject
  lateinit var mViewModelFactory: ViewModelProvider.Factory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // Inject dependencies
    onComponentCreated(getComponent())
    // Bind the view and bind the viewModel to layout
    bindContentView(layoutId())
  }

  fun bindContentView(layoutId: Int) {
    binding = DataBindingUtil.setContentView(this, layoutId)
    viewModel = ViewModelProviders.of(this, mViewModelFactory).get(getViewModelClass())
    binding.setVariable(BR.viewModel, viewModel)
  }

  abstract fun getViewModelClass(): Class<VM>

  override fun onDestroy() {
    super.onDestroy()
    component = null
  }

  protected fun getComponent(): ActivityComponent {
    if (component == null) {
      component = (application as BaseApplication).getComponent().plusActivityComponent(ActivityModule(this))
    }

    return component!!
  }

  @LayoutRes protected abstract fun layoutId(): Int

  protected abstract fun onComponentCreated(component: ActivityComponent)
}
