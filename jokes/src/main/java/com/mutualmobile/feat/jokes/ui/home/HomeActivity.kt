package com.mutualmobile.feat.jokes.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.mutualmobile.feat.jokes.R
import com.mutualmobile.feat.jokes.databinding.ActivityHomeBinding
import com.mutualmobile.feat.jokes.ui.home.HomeViewState.Error
import com.mutualmobile.feat.jokes.ui.home.HomeViewState.Loading
import com.mutualmobile.feat.jokes.ui.home.HomeViewState.ShowJokes
import com.mutualmobile.feat.jokes.ui.home.about.AboutFragment
import com.mutualmobile.feat.jokes.ui.joke.ShowJokeActivity
import com.mutualmobile.feat.jokes.ui.model.UIJoke
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList
import com.mutualmobile.feat.jokes.BR
import com.mutualmobile.praxis.navigator.Navigator
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

  @Inject
  lateinit var navigator: Navigator

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
    val bundle = prepBundle(DOMJokes)
    navigator.startScreen(Intent(this, ShowJokeActivity::class.java).apply {
      putExtras(bundle)
    })
  }

  private fun prepBundle(DOMJokes: List<UIJoke>): Bundle {
    return Bundle().apply {
      putParcelableArrayList(
        ShowJokeActivity.JOKE_LIST_INTENT,
        DOMJokes as ArrayList<out Parcelable>
      )
    }
  }

  private fun showAboutFragment() {
    navigator.showFragmentDialog(AboutFragment.newInstance(), tag = "dialog")
  }

  private fun handleDataLoadingUi(loading: Boolean) {
    with(binding) {
      progressbar.isVisible = loading
      randomJokesButtonCoroutine.isEnabled = !loading
      aboutButton.isEnabled = !loading
    }
  }
}