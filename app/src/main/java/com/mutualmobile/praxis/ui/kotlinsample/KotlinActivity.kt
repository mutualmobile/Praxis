package com.mutualmobile.praxis.ui.kotlinsample

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.ActivityKotlinBinding
import com.mutualmobile.praxis.injection.component.ActivityComponent
import com.mutualmobile.praxis.ui.base.BaseActivity

class KotlinActivity : BaseActivity<ActivityKotlinBinding, KotlinViewModel>() {
  override fun getViewModelClass(): Class<KotlinViewModel> = KotlinViewModel::class.java

  override fun onComponentCreated(component: ActivityComponent) {
    component.inject(this)
  }

  override fun layoutId(): Int {
    return R.layout.activity_kotlin
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.joke.observe(this, Observer { joke ->
      binding.jokeTV.text = joke
    })
    viewModel.loadData()
  }
}
