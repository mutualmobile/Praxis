package com.mutualmobile.praxis.ui.joke

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.ActivityShowjokeBinding
import com.mutualmobile.praxis.ui.base.BaseActivity
import com.mutualmobile.praxis.ui.onboard.OnBoardViewModel
import com.tbruyelle.rxpermissions2.RxPermissions
import timber.log.Timber
import javax.inject.Inject

class ShowJokeActivity : BaseActivity<ActivityShowjokeBinding, ShowJokeViewModel>() {

  @Inject lateinit var rxPermission: RxPermissions
  @Inject lateinit var onBoardViewModel: OnBoardViewModel

  override fun getViewModelClass(): Class<ShowJokeViewModel> = ShowJokeViewModel::class.java

  override fun layoutId(): Int {
    return R.layout.activity_showjoke
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    rxPermission.setLogging(true)
    var showJokeFragment: ShowJokeFragment? = supportFragmentManager.findFragmentById(R.id.contentFrame) as ShowJokeFragment?

    if (showJokeFragment == null) {
      showJokeFragment = ShowJokeFragment()
      val transaction = supportFragmentManager.beginTransaction()
      transaction.add(R.id.contentFrame, showJokeFragment)
      transaction.commit()
    }

    Timber.d("SCOPECHECK SJ Activity VM ::" + viewModel)
    Timber.d("SCOPECHECK SJ Activity RX ::" + rxPermission)
    Timber.d("SCOPECHECK SJ Activity OBVM::" + onBoardViewModel)
    viewModel.joke.observe(this, Observer { joke ->
      binding.jokeTV.text = joke
    })
    viewModel.loadData()
  }

  companion object {
    fun start(activity: Activity) {
      activity.startActivity(Intent(activity, ShowJokeActivity::class.java))
    }
  }
}
