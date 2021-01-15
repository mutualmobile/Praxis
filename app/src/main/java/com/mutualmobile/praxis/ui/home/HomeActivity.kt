package com.mutualmobile.praxis.ui.home

import android.os.Bundle
import android.os.Parcelable
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.data.remote.model.Joke
import com.mutualmobile.praxis.databinding.ActivityHomeBinding
import com.mutualmobile.praxis.ui.base.ActivityNavigator
import com.mutualmobile.praxis.ui.base.BaseActivity
import com.mutualmobile.praxis.ui.home.HomeViewState.Error
import com.mutualmobile.praxis.ui.home.HomeViewState.Loading
import com.mutualmobile.praxis.ui.home.HomeViewState.ShowJokes
import com.mutualmobile.praxis.ui.home.about.AboutFragment
import com.mutualmobile.praxis.ui.joke.ShowJokeActivity
import java.util.ArrayList

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeVM>() {
  override fun getViewModelClass(): Class<HomeVM> = HomeVM::class.java

  override fun layoutId(): Int = R.layout.activity_home

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    addListeners()
    addObservers()
  }

  private fun addListeners() {
    with(binding) {
      randomJokesButtonCoroutine.setOnClickListener { viewModel.loadJokes() }
      aboutButton.setOnClickListener { showAboutFragment() }
    }
  }

  private fun addObservers() {
    viewModel.viewState.observe(this, Observer { state ->
      when (state) {
        is Loading -> {
          handleDataLoadingUi(true)
        }
        is ShowJokes -> {
          handleDataLoadingUi(false)
          showJokeActivity(state.jokes)
        }
        is Error -> {
          handleDataLoadingUi(false)
        }
      }
    })
  }

  private fun showJokeActivity(jokes: List<Joke>) {
    val bundle = Bundle().apply {
      putParcelableArrayList(ShowJokeActivity.JOKE_LIST_INTENT, jokes as ArrayList<out Parcelable>)
    }

    ActivityNavigator.startActivityWithDataAndAnimation(
        ShowJokeActivity::class.java, bundle, R.anim.slide_left_in, R.anim.slide_left_out, this
    )
  }

  private fun showAboutFragment() {
    val fragment = AboutFragment.newInstance()
    fragment.show(supportFragmentManager, "dialog")
  }

  private fun handleDataLoadingUi(loading: Boolean) {
    with(binding) {
      progressbar.isVisible = loading
      randomJokesButtonCoroutine.isEnabled = !loading
      aboutButton.isEnabled = !loading
    }
  }
}