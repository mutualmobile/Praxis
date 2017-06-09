package com.mutualmobile.praxis.ui.kotlinsample

import android.os.Bundle
import android.widget.Toast
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.ActivityKotlinBinding
import com.mutualmobile.praxis.injection.component.ActivityComponent
import com.mutualmobile.praxis.ui.base.BaseActivity

class KotlinActivity : BaseActivity<ActivityKotlinBinding, KotlinView, KotlinViewModel>(), KotlinView {
  override fun showToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
  }

  override fun onComponentCreated(component: ActivityComponent?) {
    component?.inject(this)
  }

  override fun layoutId(): Int {
    return R.layout.activity_kotlin
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.loadData()
  }
}
