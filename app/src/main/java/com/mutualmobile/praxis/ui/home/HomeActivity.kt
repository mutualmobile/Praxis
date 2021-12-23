package com.mutualmobile.praxis.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.mutualmobile.praxis.BR
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.ActivityHomeBinding
import com.mutualmobile.praxis.ui.github.GithubReposActivity
import com.mutualmobile.praxis.ui.home.HomeViewState.Error
import com.mutualmobile.praxis.ui.home.HomeViewState.Loading
import com.mutualmobile.praxis.ui.home.HomeViewState.ShowJokes
import com.mutualmobile.praxis.ui.home.about.AboutFragment
import com.mutualmobile.praxis.ui.joke.ShowJokeActivity
import com.mutualmobile.praxis.ui.model.UIJoke
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

  private lateinit var binding: ActivityHomeBinding
  private val viewModel: HomeVM by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
    binding.setVariable(BR.viewModel, viewModel)
    addListeners()
    addObservers()
  }

  private fun addListeners() {
    with(binding) {
      randomJokesButtonCoroutine.setOnClickListener { viewModel.loadJokes() }
      btnGithubTrendingRepos.setOnClickListener {
        startActivity(Intent(this@HomeActivity, GithubReposActivity::class.java))
      }
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

  private fun showJokeActivity(DOMJokes: List<UIJoke>) {
    val bundle = Bundle().apply {
      putParcelableArrayList(
        ShowJokeActivity.JOKE_LIST_INTENT,
        DOMJokes as ArrayList<out Parcelable>
      )
    }
    startActivity(Intent(this, ShowJokeActivity::class.java).apply {
      putExtras(bundle)
    })
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