package com.mutualmobile.praxis.ui.joke

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mutualmobile.praxis.data.model.Joke
import com.mutualmobile.praxis.utils.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ShowJokeViewModelTest {
  /**
   * A JUnit Test Rule that swaps the background executor used by the Architecture Components
   * with a different one which executes each task synchronously.
   */
  @get: Rule
  val instantTaskExecutorRule = InstantTaskExecutorRule()

  @Test
  fun showJoke_whenArrayListIsGiven_returnsFormattedText(){

    //Given a empty viewmodel
    val showJokeViewModel = ShowJokeViewModel()

    //When an arraylist of jokes are passed to showJoke
    val list = arrayListOf(
        Joke(1,"Joke but plese don't laugh &quot;"),
        Joke(2, "I am intelligent")
    )

    showJokeViewModel.showJoke(list)

    //then output value will be formatted
    val value = showJokeViewModel.jokeStringLiveData.getOrAwaitValue()

    assertEquals("Joke but plese don't laugh \"\n\nI am intelligent\n\n", value)
  }

  @Test
  fun showJoke_whenEmptyArrayListIsGiven_returnsEmptyString(){

    //Given a fresh viewmodel
    val showJokeViewModel = ShowJokeViewModel()

    //when an arraylist of empty joke are passed to showJoke
    val list = arrayListOf<Joke>()

    showJokeViewModel.showJoke(list)

    //then output value will be formatted
    val value = showJokeViewModel.jokeStringLiveData.getOrAwaitValue()

    assertEquals("",value)
  }


}