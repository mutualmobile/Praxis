package com.mutualmobile.feat.githubrepos

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.mutualmobile.feat.githubrepos.ui.github.repolist.GithubReposVM
import com.mutualmobile.feat.githubrepos.ui.model.UIRepoMapper
import com.mutualmobile.praxis.data.remote.model.RepoListResponseMapper
import com.mutualmobile.praxis.data.repository.GithubRepoImpl
import com.mutualmobile.praxis.data.sources.GithubReposRemoteSource
import com.mutualmobile.praxis.domain.model.DOMOwner
import com.mutualmobile.praxis.domain.model.DOMRepo
import com.mutualmobile.praxis.domain.model.request.GithubReposSearchRequest
import com.mutualmobile.praxis.domain.repository.IGithubRepo
import com.mutualmobile.praxis.domain.usecases.GetGithubTrendingReposUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class GithubReposVMTest {

    @MockK
    lateinit var getGithubReposUseCase: GetGithubTrendingReposUseCase

    @MockK
    lateinit var uiRepoMapper: UIRepoMapper

    private lateinit var gitSearchReq:GithubReposSearchRequest

    private lateinit var githubRepoVM: GithubReposVM


    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()

    }


    @Test
    fun `fetch trending repos with no  exception`() {
        runTest {
            launch {

                 val DEFAULT_QUERY = "flutter"

                val currentQuery = MutableLiveData(DEFAULT_QUERY)


                coEvery {
                    getGithubReposUseCase.perform()
                }

                githubRepoVM = GithubReposVM(getGithubReposUseCase, uiRepoMapper)
                githubRepoVM.getGitHubTrendingRepos()

            }
        }
    }


}