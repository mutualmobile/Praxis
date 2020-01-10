package com.mutualmobile.praxis.ui.joke

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mutualmobile.praxis.data.model.Joke
import com.mutualmobile.praxis.getOrAwaitValue
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [28])
@RunWith(AndroidJUnit4::class)
class ShowJokeViewModelTest{

  /*
   * A JUnit Test Rule that swaps the background executor used by the
   * Architecture Components with a different one which executes each task synchronously.
   */
  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  @Test
  fun showJoke_whenArrayListIsGiven_withFormattedText(){

    //Given a fresh viewmodel
    val showJokeViewModel = ShowJokeViewModel()

    //when an arraylist of jokes are passed to showJoke
    val list = arrayListOf<Joke>(
        Joke(1,"Joke but plese don't laugh &quot;"),
        Joke(2, "I am intelligent")
    )

    showJokeViewModel.showJoke(list)

    //then output value will be formatted
    val value = showJokeViewModel.jokeStringLiveData.getOrAwaitValue()

    assertThat("Joke but plese don't laugh \"\n\nI am intelligent\n\n",
        `is`(value))
  }
  @Test
  fun showJoke_whenEmptyArrayListIsGiven_withEmptyString(){

    //Given a fresh viewmodel
    val showJokeViewModel = ShowJokeViewModel()

    //when an arraylist of empty joke are passed to showJoke
    val list = arrayListOf<Joke>()

    showJokeViewModel.showJoke(list)

    //then output value will be formatted
    val value = showJokeViewModel.jokeStringLiveData.getOrAwaitValue()

    assertThat("", `is`(value))
  }

}