package com.mutualmobile.praxis.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.ActivityHomeBinding
import com.mutualmobile.praxis.ui.base.BaseActivity
import com.mutualmobile.praxis.ui.home.about.AboutFragment
import com.mutualmobile.praxis.ui.joke.ShowJokeActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {
  override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

  override fun layoutId(): Int = R.layout.activity_home

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding.randomJokesButton.setOnClickListener { viewModel.loadData() }
    binding.aboutButton.setOnClickListener { showAboutFragment() }

    viewModel.dataLoading.observe(this, Observer { handleDataLoadingUi(it!!) })

    viewModel.dataJokes.observe(this, Observer { it ->
      it?.let {
        val bundle = Bundle()
        bundle.putParcelableArrayList(ShowJokeActivity.JOKE_LIST_INTENT, it.value)
        showJokeActivity(bundle)
      }
    })
  }

  private fun showJokeActivity(bundle: Bundle) {
    navigator.startActivityWithDataAndAnimation(ShowJokeActivity::class.java, bundle, R.anim.slide_left_in, R.anim.slide_left_out)
  }

  private fun showAboutFragment() {
    val fragment = AboutFragment.newInstance()
    fragment.show(supportFragmentManager, "dialog")
  }

  private fun handleDataLoadingUi(loading: Boolean) {
    binding.progressbar.visibility = if (loading) View.VISIBLE else View.INVISIBLE
    binding.randomJokesButton.isEnabled = !loading
    binding.aboutButton.isEnabled = !loading
  }
}