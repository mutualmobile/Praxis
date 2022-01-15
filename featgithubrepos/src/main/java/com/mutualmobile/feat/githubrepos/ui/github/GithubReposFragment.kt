package com.mutualmobile.feat.githubrepos.ui.github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.mutualmobile.feat.githubrepos.databinding.FragmentReposBinding
import com.mutualmobile.feat.githubrepos.ui.github.adapter.ReposLoadStateAdapter
import com.mutualmobile.feat.githubrepos.ui.github.adapter.ReposPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GithubReposFragment : Fragment() {

  private val viewModel by viewModels<GithubReposVM>()
  private lateinit var binding: FragmentReposBinding

  private var reposPagingAdapter: ReposPagingAdapter = ReposPagingAdapter()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentReposBinding.inflate(inflater, container, false)
    binding.lifecycleOwner = this
    initRecyclerView()
    fetchTrendingRepos()
    return binding.root
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
}
