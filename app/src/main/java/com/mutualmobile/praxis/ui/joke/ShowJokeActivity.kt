package com.mutualmobile.praxis.ui.joke

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mutualmobile.praxis.BR
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.ActivityShowjokeBinding
import com.mutualmobile.praxis.domain.model.Joke
import com.mutualmobile.praxis.ui.base.ActivityNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowJokeActivity : AppCompatActivity() {

  companion object {
    const val JOKE_LIST_INTENT = "Joke_list_intent"
  }

  private val viewModel by viewModels<ShowJokeVM>()
  private lateinit var binding: ActivityShowjokeBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this,R.layout.activity_showjoke)
    binding.setVariable(BR.viewModel, viewModel)
    val jokeList = intent.getParcelableArrayListExtra<Joke>(JOKE_LIST_INTENT)
    binding.lifecycleOwner = this
    viewModel.showJoke(jokeList)
    initToolbar()
  }

  private fun initToolbar() {
    setSupportActionBar(binding.toolbar)
    supportActionBar?.setHomeButtonEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    binding.toolbar.setNavigationOnClickListener { onBackPressed() }
  }

  override fun onBackPressed() {
    ActivityNavigator.finishActivityWithAnimation(R.anim.slide_right_in, R.anim.slide_right_out, this)
  }

}
