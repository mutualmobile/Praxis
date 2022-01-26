package com.mutualmobile.feat.jokes.ui.joke.jokedetails

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.mutualmobile.feat.jokes.ui.joke.InMemoryDataTemp
import com.mutualmobile.feat.jokes.ui.model.UIJoke
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class JokeDetailVMTest {

    @MockK
    lateinit var savedStateHandle: SavedStateHandle
    lateinit var jokeDetailVM: JokeDetailVM

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
    fun `test that uiState emits SuccessState on getting a non-null UIJoke`() = runTest {
        launch {
            coEvery {
                savedStateHandle.get<Long>(any())
            } returns 0

            InMemoryDataTemp.jokes = listOf(
                UIJoke(0, "TestJoke")
            )
            jokeDetailVM = JokeDetailVM(savedStateHandle)

            jokeDetailVM.uiState.test {
                assert(awaitItem() is JokeDetailVM.UiState.SuccessState)
            }
        }
    }

    @Test
    fun `test that uiState emits LoadingState on getting a null UIJoke`() = runTest {
        launch {
            coEvery {
                savedStateHandle.get<Long>(any())
            } returns 0

            InMemoryDataTemp.jokes = null
            jokeDetailVM = JokeDetailVM(savedStateHandle)

            jokeDetailVM.uiState.test {
                assert(awaitItem() is JokeDetailVM.UiState.LoadingState)
            }
        }
    }
}