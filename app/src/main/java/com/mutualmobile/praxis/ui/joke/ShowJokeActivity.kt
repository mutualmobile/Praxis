package com.mutualmobile.praxis.ui.joke

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.ActivityShowjokeBinding
import com.mutualmobile.praxis.ui.base.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.android.AndroidInjection
import timber.log.Timber
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
    var showJokeFragment: ShowJokeFragment? = supportFragmentManager.findFragmentById(R.id.contentFrame) as ShowJokeFragment?

    if (showJokeFragment == null) {
      showJokeFragment = ShowJokeFragment()
      val transaction = supportFragmentManager.beginTransaction()
      transaction.add(R.id.contentFrame, showJokeFragment)
      transaction.commit()
    }

    Timber.d("TRDDRT Activity ::" + viewModel)
    Timber.d("TRDDRT Fragment RX ::" + rxPermission)
    viewModel.joke.observe(this, Observer { joke ->
      binding.jokeTV.text = joke
    })
    viewModel.loadData()
  }
}
