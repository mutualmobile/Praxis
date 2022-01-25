package com.mutualmobile.feat.jokes.ui.joke.home

import app.cash.turbine.test
import com.mutualmobile.feat.jokes.ui.model.UIJokeMapper
import com.mutualmobile.praxis.data.repository.JokesRepoImpl
import com.mutualmobile.praxis.domain.SafeResult
import com.mutualmobile.praxis.domain.model.DOMJoke
import com.mutualmobile.praxis.domain.model.DOMJokeList
import com.mutualmobile.praxis.domain.usecases.GetFiveRandomJokesUseCase
import com.mutualmobile.praxis.navigator.ComposeNavigator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeVMTest {

    private val uiJokesMapper = UIJokeMapper()

    @MockK
    private lateinit var navigator: ComposeNavigator
    private lateinit var getFiveRandomJokesUseCase: GetFiveRandomJokesUseCase
    private lateinit var homeVM: HomeVM

    @MockK
    private lateinit var jokesRepoImpl: JokesRepoImpl

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
    fun `test that loadJokes() completes on success response`() = runTest {

        // This is mocking
        coEvery {
            navigator.observeResult<String>(any())
        } returns emptyFlow()

        coEvery {
            jokesRepoImpl.getFiveRandomJokes()
        } returns SafeResult.Success(
            data = DOMJokeList(
                type = "success",
                DOMJokes = listOf(
                    DOMJoke(0, "Test Joke")
                )
            )
        )

        getFiveRandomJokesUseCase = GetFiveRandomJokesUseCase(jokesRepoImpl)

        homeVM = HomeVM(getFiveRandomJokesUseCase, uiJokesMapper, navigator)

        homeVM.viewState.test {
            assert(awaitItem() is HomeViewState.Loading)
            assert(awaitItem() is HomeViewState.ShowJokes)
        }
    }

    @Test
    fun `test that loadJokes() fails on failed response`() = runTest {

        // This is mocking
        coEvery {
            navigator.observeResult<String>(any())
        } returns emptyFlow()

        coEvery {
            jokesRepoImpl.getFiveRandomJokes()
        } returns SafeResult.Failure()

        getFiveRandomJokesUseCase = GetFiveRandomJokesUseCase(jokesRepoImpl)

        homeVM = HomeVM(getFiveRandomJokesUseCase, uiJokesMapper, navigator)

        homeVM.viewState.test {
            assert(awaitItem() is HomeViewState.Loading)
            assert(awaitItem() is HomeViewState.Error)
        }
    }
}