package com.mutualmobile.feat.jokes.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutualmobile.feat.jokes.ui.model.UIJoke
import com.mutualmobile.feat.jokes.ui.model.UIJokeMapper
import com.mutualmobile.praxis.domain.SafeResult
import com.mutualmobile.praxis.domain.usecases.GetFiveRandomJokesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
  private val getFiveRandomJokesUseCase: GetFiveRandomJokesUseCase,
  private val uiJokesMapper: UIJokeMapper
) : ViewModel() {

  var viewState = mutableStateOf<HomeViewState>(HomeViewState.Loading)
    private set

  init {
    loadJokes()
  }

  private fun showJoke(DOMJokeList: List<UIJoke?>?): String {
    var jokeString = ""
    DOMJokeList?.let {
      for (joke in DOMJokeList) {
        jokeString = jokeString + joke?.joke?.replace("&quot;", "\"") + "\n\n"
      }
    }
    return jokeString
  }

  fun loadJokes() {
    viewState.value = HomeViewState.Loading
    viewModelScope.launch {
      when (val result = getFiveRandomJokesUseCase.perform()) {
        is SafeResult.Success -> {
          viewState.value =
            HomeViewState.ShowJokes(showJoke(result.data.map {
              uiJokesMapper.mapToPresentation(it)
            }))
        }
        is SafeResult.Failure -> {
          Timber.e("onError")
          viewState.value = HomeViewState.Error(result.message)
        }
        SafeResult.NetworkError -> {
          viewState.value = HomeViewState.Error("Network Error")
        }
      }
    }
  }
}

sealed class HomeViewState {
  object Loading : HomeViewState()
  class ShowJokes(val jokes: String) : HomeViewState()
  class Error(val message: String) : HomeViewState()
}