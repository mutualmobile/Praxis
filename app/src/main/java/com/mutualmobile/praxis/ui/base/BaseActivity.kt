package com.mutualmobile.praxis.ui.base

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

abstract class BaseActivity<B : ViewDataBinding, V : MvvmView, VM : MvvmViewModel<V>> : AppCompatActivity() {
  private var component: ActivityComponent? = null

  @Inject lateinit var viewModel: VM

  protected lateinit var binding: B

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // Inject dependencies
    onComponentCreated(getComponent())
    // Bind the view and bind the viewModel to layout
    bindContentView(layoutId())
  }

  fun bindContentView(layoutId: Int) {
    binding = DataBindingUtil.setContentView(this, layoutId)
    @Suppress("UNCHECKED_CAST")
    viewModel.attachView(this as V)
    binding.setVariable(BR.viewModel, viewModel)
  }

  override fun onDestroy() {
    super.onDestroy()
    viewModel.detachView()

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
