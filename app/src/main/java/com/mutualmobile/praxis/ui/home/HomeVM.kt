package com.mutualmobile.praxis.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mutualmobile.praxis.data.SafeResult
import com.mutualmobile.praxis.data.remote.model.Joke
import com.mutualmobile.praxis.domain.usecases.GetFiveRandomJokesUseCase
import com.mutualmobile.praxis.ui.base.BaseVM
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class HomeVM @Inject constructor(
  private val getFiveRandomJokesUseCase: GetFiveRandomJokesUseCase
) : BaseVM() {

  private var _viewState: MutableLiveData<HomeViewState> = MutableLiveData()
  val viewState: LiveData<HomeViewState> = _viewState

  fun loadJokes() {
    _viewState.value = HomeViewState.Loading
    viewModelScope.launch {
      when (val result = getFiveRandomJokesUseCase.perform()) {
        is SafeResult.Success -> {
          _viewState.value = HomeViewState.ShowJokes(result.data.value)
        }
        is SafeResult.Failure -> {
          Timber.e("onError")
          _viewState.value = HomeViewState.Error(result.message)
        }
      }
    }
  }
}

sealed class HomeViewState {
  object Loading : HomeViewState()
  class ShowJokes(val jokes: List<Joke>) : HomeViewState()
  class Error(val message: String) : HomeViewState()
}