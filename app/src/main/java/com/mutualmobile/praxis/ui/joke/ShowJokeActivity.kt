package com.mutualmobile.praxis.ui.joke

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.ActivityShowjokeBinding
import com.mutualmobile.praxis.ui.base.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.android.AndroidInjection
import javax.inject.Inject

class ShowJokeActivity : BaseActivity<ActivityShowjokeBinding, ShowJokeViewModel>() {

  @Inject lateinit var rxPermission: RxPermissions

  override fun getViewModelClass(): Class<ShowJokeViewModel> = ShowJokeViewModel::class.java

  override fun onComponentCreated() {
    AndroidInjection.inject(this)
  }

  override fun layoutId(): Int {
    return R.layout.activity_showjoke
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    rxPermission.setLogging(true)
    viewModel.joke.observe(this, Observer { joke ->
      binding.jokeTV.text = joke
    })
    viewModel.loadData()
  }
}
