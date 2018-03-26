package com.mutualmobile.praxis.ui.onboard

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.ActivityOnboardBinding
import com.mutualmobile.praxis.ui.base.BaseActivity
import com.mutualmobile.praxis.ui.joke.ShowJokeActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import timber.log.Timber
import javax.inject.Inject

class OnBoardActivity : BaseActivity<ActivityOnboardBinding, OnBoardViewModel>() {
  @Inject lateinit var rxPermission: RxPermissions
  @Inject lateinit var uuid: String

  override fun getViewModelClass() = OnBoardViewModel::class.java

  override fun layoutId() = R.layout.activity_onboard

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Timber.d("SCOPECHECK OB Activity VM ::" + viewModel)
    Timber.d("SCOPECHECK OB Activity RX ::" + rxPermission)
    Timber.d("UUID: {$uuid}")

    viewModel.onBoardMessage.observe(this, Observer { message ->
      binding.onBoardMessage.text = message
    })
    binding.skipButton.setOnClickListener({ view ->
      ShowJokeActivity.start(this)
    })
  }
}
