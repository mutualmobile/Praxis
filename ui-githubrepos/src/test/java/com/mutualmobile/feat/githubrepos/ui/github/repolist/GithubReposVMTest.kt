package com.mutualmobile.feat.githubrepos.ui.github.repolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import app.cash.turbine.test
import com.mutualmobile.feat.githubrepos.ui.model.UIRepoMapper
import com.mutualmobile.feat.githubrepos.utils.getOrAwaitValue
import com.mutualmobile.praxis.data.repository.GithubRepoImpl
import com.mutualmobile.praxis.domain.model.DOMOwner
import com.mutualmobile.praxis.domain.model.DOMRepo
import com.mutualmobile.praxis.domain.usecases.GetGithubTrendingReposUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class GithubReposVMTest {

    /**
     * Important for your test's execution while testing LiveData.
     * Fixes error message: "Android looper not mocked"
     */
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var githubReposVM: GithubReposVM

    @MockK
    lateinit var githubRepoImpl: GithubRepoImpl

    lateinit var githubTrendingReposUseCase: GetGithubTrendingReposUseCase

    @MockK
    lateinit var uiRepoMapper: UIRepoMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
        Dispatchers.setMain(StandardTestDispatcher())
        uiRepoMapper = UIRepoMapper()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test that trendingGithubRepos() completes without exception`() = runTest {
        launch {
            val testRepo = PagingData.from(
                listOf(
                    DOMRepo(
                        id = 0,
                        name = "Test",
                        fullName = "Test",
                        description = "Test",
                        url = "Test",
                        stars = 0,
                        forks = 0,
                        language = "Test",
                        watchers = 0,
                        owner = DOMOwner(
                            id = 0,
                            login = "Test",
                            avatarUrl = "Test"
                        ),
                        createDate = "Test",
                        updateDate = "Test",
                        openIssues = 0
                    )
                )
            )

            coEvery {
                githubRepoImpl.getTrendingRepos("flutter")
            } returns flowOf(testRepo)

            githubTrendingReposUseCase = GetGithubTrendingReposUseCase(githubRepoImpl)

            githubReposVM = GithubReposVM(
                githubTrendingReposUseCase,
                uiRepoMapper
            )

            githubReposVM.getGitHubTrendingRepos()
            delay(1) // Because we need our liveData to be assigned first before testing

            githubReposVM.reposFlowLiveData.getOrAwaitValue().test {
                assert(awaitItem() == testRepo)
                cancelAndConsumeRemainingEvents()
            }
        }
    }
}