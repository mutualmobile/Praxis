package com.mutualmobile.praxis.ui.home

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.ActivityHomeBinding
import com.mutualmobile.praxis.ui.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {
  override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

  override fun layoutId(): Int = R.layout.activity_home

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding.randomJokesButton.setOnClickListener { viewModel.loadData() }

    viewModel.dataLoading.observe(this, Observer { handleDataLoadingUi(it!!) })
  }

  private fun handleDataLoadingUi(loading: Boolean) {
    binding.progressbar.visibility = if (loading) View.VISIBLE else View.INVISIBLE
    binding.randomJokesButton.isEnabled = !loading
    binding.aboutButton.isEnabled = !loading
  }
}