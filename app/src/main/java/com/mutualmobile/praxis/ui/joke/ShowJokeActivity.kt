package com.mutualmobile.praxis.ui.joke

import android.os.Bundle
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.data.remote.model.Joke
import com.mutualmobile.praxis.databinding.ActivityShowjokeBinding
import com.mutualmobile.praxis.ui.base.ActivityNavigator
import com.mutualmobile.praxis.ui.base.BaseActivity

class ShowJokeActivity : BaseActivity<ActivityShowjokeBinding, ShowJokeVM>() {

  companion object {
    const val JOKE_LIST_INTENT = "Joke_list_intent"
  }

  override fun getViewModelClass(): Class<ShowJokeVM> = ShowJokeVM::class.java

  override fun layoutId(): Int {
    return R.layout.activity_showjoke
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
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
