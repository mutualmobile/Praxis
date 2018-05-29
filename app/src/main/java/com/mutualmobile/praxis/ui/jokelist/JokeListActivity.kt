package com.mutualmobile.praxis.ui.jokelist

import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.ActivityJokeListBinding
import com.mutualmobile.praxis.ui.base.BaseActivity

class JokeListActivity: BaseActivity<ActivityJokeListBinding, JokeListViewModel>() {
  override fun getViewModelClass(): Class<JokeListViewModel> = JokeListViewModel::class.java

  override fun layoutId(): Int = R.layout.activity_joke_list
}