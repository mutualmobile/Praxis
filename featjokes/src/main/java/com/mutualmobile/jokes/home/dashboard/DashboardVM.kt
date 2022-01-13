package com.mutualmobile.jokes.home.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutualmobile.jokes.home.InMemoryDataTemp
import com.mutualmobile.jokes.home.dashboard.HomeViewState.Error
import com.mutualmobile.jokes.home.dashboard.HomeViewState.Loading
import com.mutualmobile.jokes.home.dashboard.HomeViewState.ShowJokes
import com.mutualmobile.jokes.model.JokeView
import com.mutualmobile.jokes.model.JokeViewMapper
import com.mutualmobile.praxis.domain.SafeResult
import com.mutualmobile.praxis.domain.model.Joke
import com.mutualmobile.praxis.domain.usecases.GetFiveRandomJokesUseCase
import com.mutualmobile.praxis.navigator.Navigator
import com.mutualmobile.praxis.navigator.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
  private val getFiveRandomJokesUseCase: GetFiveRandomJokesUseCase,
  private val jokesViewMapper: JokeViewMapper,
  private val navigator: Navigator
) : ViewModel() {

  var viewState = MutableStateFlow<HomeViewState>(Loading)
    private set

  init {
    loadJokes()
  }

  private fun loadJokes() {
    viewState.value = Loading
    viewModelScope.launch {
      when (val result = getFiveRandomJokesUseCase.performAsync()) {
        is SafeResult.Success -> {
          val jokes = uiJokes(result)
          InMemoryDataTemp.jokeViews = jokes
          viewState.value = ShowJokes(jokes)
        }
        is SafeResult.Failure -> {
          Timber.e("onError")
          viewState.value = Error(result.message)
        }
        SafeResult.NetworkError -> {
          viewState.value = Error("Network Error")
        }
      }
    }
  }

  private fun uiJokes(result: SafeResult.Success<List<Joke>>) =
    result.data.map {
      jokesViewMapper.mapToPresentation(it)
    }

  fun showJoke(jokeId: Int) {
    navigator.navigate(Screen.JokeDetail.createRoute(jokeId.toString()))
  }
}

sealed class HomeViewState {
  object Loading : HomeViewState()
  class ShowJokes(val jokeViews: List<JokeView>) : HomeViewState()
  class Error(val message: String) : HomeViewState()
}