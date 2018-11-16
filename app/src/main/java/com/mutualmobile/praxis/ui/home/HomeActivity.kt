package com.mutualmobile.praxis.ui.home

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.ActivityHomeBinding
import com.mutualmobile.praxis.ui.base.BaseActivity
import com.mutualmobile.praxis.ui.joke.ShowJokeActivity
import com.mutualmobile.praxis.ui.profile.ProfileActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {
  override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

  override fun layoutId(): Int = R.layout.activity_home

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding.randomJokesButton.setOnClickListener { viewModel.loadData() }
    binding.aboutButton.setOnClickListener { showAboutFragment() }
    binding.profileButton.setOnClickListener { showProfileActivity() }

    viewModel.dataLoading.observe(this, Observer { handleDataLoadingUi(it!!) })

    viewModel.dataJokes.observe(this, Observer { it ->
      it?.let {
        val bundle = Bundle()
        bundle.putParcelableArrayList(ShowJokeActivity.JOKE_LIST_INTENT, it.value)
        showJokeListActivity(bundle)
      }
    })
  }

  private fun showProfileActivity() {
    navigator.startActivity(ProfileActivity::class.java)
  }

  private fun showJokeListActivity(bundle: Bundle) {
    navigator.startActivityWithData(ShowJokeActivity::class.java, bundle)
  }

  private fun showAboutFragment() {
    val fragment = AboutFragment.newInstance()
    fragment.show(fragmentManager, "dialog")
  }

  private fun handleDataLoadingUi(loading: Boolean) {
    binding.progressbar.visibility = if (loading) View.VISIBLE else View.INVISIBLE
    binding.randomJokesButton.isEnabled = !loading
    binding.aboutButton.isEnabled = !loading
  }
}