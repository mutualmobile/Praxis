package com.mutualmobile.feat.jokes.ui.joke

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mutualmobile.feat.jokes.R
import com.mutualmobile.feat.jokes.databinding.ActivityShowjokeBinding
import com.mutualmobile.feat.jokes.ui.model.UIJoke
import dagger.hilt.android.AndroidEntryPoint
import com.mutualmobile.feat.jokes.BR

@AndroidEntryPoint
class ShowJokeActivity : AppCompatActivity() {

  companion object {
    const val JOKE_LIST_INTENT = "Joke_list_intent"
  }

  private val viewModel by viewModels<ShowJokeVM>()
  private lateinit var binding: ActivityShowjokeBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_showjoke)
    binding.setVariable(BR.viewModel, viewModel)
    val jokeList = intent.getParcelableArrayListExtra<UIJoke>(JOKE_LIST_INTENT)
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
    finish()
  }

}
