package com.alamodrafthouse.ui.kotlinsample

import android.os.Bundle
import android.widget.Toast
import com.alamodrafthouse.R
import com.alamodrafthouse.databinding.ActivityKotlinBinding
import com.alamodrafthouse.injection.component.ActivityComponent
import com.alamodrafthouse.ui.base.BaseActivity

class KotlinActivity : BaseActivity<ActivityKotlinBinding, KotlinView, KotlinViewModel>(), KotlinView {
  override fun showToast() {
    Toast.makeText(this, "Woooooooooow", Toast.LENGTH_LONG).show()
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
