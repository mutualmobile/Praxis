package com.mutualmobile.praxis.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutualmobile.baseclean.SafeResult
import com.mutualmobile.praxis.domain.usecases.GetFiveRandomJokesUseCase
import com.mutualmobile.praxis.ui.model.UIJoke
import com.mutualmobile.praxis.ui.model.UIJokeMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
  private val getFiveRandomJokesUseCase: GetFiveRandomJokesUseCase,
  private val uiJokesMapper: UIJokeMapper
) : ViewModel() {

  private var _viewState: MutableLiveData<HomeViewState> = MutableLiveData()
  val viewState: LiveData<HomeViewState> = _viewState

  fun loadJokes() {
    _viewState.value = HomeViewState.Loading
    viewModelScope.launch {
      when (val result = getFiveRandomJokesUseCase.perform()) {
        is SafeResult.Success -> {
          _viewState.value =
            HomeViewState.ShowJokes(result.data.map {
              uiJokesMapper.mapToPresentation(it)
            })
        }
        is SafeResult.Failure -> {
          Timber.e("onError")
          _viewState.value = HomeViewState.Error(result.message)
        }
        SafeResult.NetworkError -> {
          _viewState.value = HomeViewState.Error("Network Error")
        }
      }
    }
  }
}

sealed class HomeViewState {
  object Loading : HomeViewState()
  class ShowJokes(val jokes: List<UIJoke>) : HomeViewState()
  class Error(val message: String) : HomeViewState()
}