package com.mutualmobile.feat.githubrepos.ui.github

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.mutualmobile.feat.githubrepos.ui.model.UIRepo
import com.mutualmobile.feat.githubrepos.ui.model.UIRepoMapper
import com.mutualmobile.praxis.domain.model.DOMRepo
import com.mutualmobile.praxis.domain.model.request.GithubReposSearchRequest
import com.mutualmobile.praxis.domain.usecases.GetGithubTrendingReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GithubReposVM @Inject constructor(
  private val getGithubReposUseCase: GetGithubTrendingReposUseCase,
  private val uiRepoMapper: UIRepoMapper
) : ViewModel() {

  private val currentQuery = MutableLiveData(DEFAULT_QUERY)

  companion object {
    private const val DEFAULT_QUERY = "flutter"
  }

  fun mapToUiRepo(domRepo: DOMRepo): UIRepo {
    return uiRepoMapper.mapToPresentation(domRepo)
  }

  suspend fun getGitHubTrendingRepos(): Flow<PagingData<DOMRepo>> {
    return getGithubReposUseCase.perform(
      GithubReposSearchRequest(query = currentQuery.value.orEmpty())
    )
  }
}