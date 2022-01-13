package com.mutualmobile.jokes.home.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mutualmobile.jokes.home.InMemoryDataTemp
import com.mutualmobile.jokes.home.details.JokeDetailsVM.UiState.LoadingState
import com.mutualmobile.jokes.home.details.JokeDetailsVM.UiState.SuccessState
import com.mutualmobile.jokes.model.JokeView
import com.mutualmobile.praxis.navigator.Navigator
import com.mutualmobile.praxis.navigator.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class JokeDetailsVM @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val navigator: Navigator
) : ViewModel() {
  private val jokeId = savedStateHandle.get<Long>(Screen.JokeDetail.navArguments.first().name)!!

  var uiState = MutableStateFlow<UiState>(LoadingState)
    private set

  init {
    val joke = InMemoryDataTemp.jokeViews?.find { it.jokeId == jokeId.toInt() }
    joke?.let {
      uiState.value = SuccessState(joke)
    }
  }

  sealed class UiState {
    object LoadingState : UiState()
    data class SuccessState(
      val jokeView: JokeView,
    ) : UiState()

    object ErrorState : UiState()
  }

}