package com.mutualmobile.praxis.ui.github

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.mutualmobile.praxis.BR
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.ActivityReposBinding
import com.mutualmobile.praxis.ui.github.adapter.ReposPagingAdapter
import com.mutualmobile.praxis.ui.github.adapter.ReposLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GithubReposActivity : AppCompatActivity() {

  private val viewModel by viewModels<GithubReposVM>()
  private lateinit var binding: ActivityReposBinding

  private var reposPagingAdapter: ReposPagingAdapter = ReposPagingAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_repos)
    binding.setVariable(BR.viewModel, viewModel)
    binding.lifecycleOwner = this
    initToolbar()
    initRecyclerView()
    fetchTrendingRepos()
  }

  private fun initToolbar() {
    setSupportActionBar(binding.toolbar)
    supportActionBar?.setHomeButtonEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    binding.toolbar.setNavigationOnClickListener { onBackPressed() }
  }

  private fun initRecyclerView() {
    with(binding.rvRepoList) {
      layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
      adapter = reposPagingAdapter.withLoadStateHeaderAndFooter(
        header = ReposLoadStateAdapter(),
        footer = ReposLoadStateAdapter()
      )
    }
  }

  private fun fetchTrendingRepos() {
    lifecycleScope.launch {
      viewModel.getGitHubTrendingRepos().collect { pagingData ->
        reposPagingAdapter.submitData(pagingData = pagingData.map { viewModel.mapToUiRepo(it) })
      }
    }
  }

  override fun onBackPressed() {
    finish()
  }
}
