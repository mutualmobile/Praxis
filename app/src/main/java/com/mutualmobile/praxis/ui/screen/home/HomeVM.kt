package com.mutualmobile.praxis.ui.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutualmobile.praxis.data.SafeResult
import com.mutualmobile.praxis.domain.model.Joke
import com.mutualmobile.praxis.data.usecases.GetFiveRandomJokesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(private val getFiveRandomJokesUseCase: GetFiveRandomJokesUseCase) : ViewModel() {

    val viewState: MutableState<HomeViewState> = mutableStateOf(HomeViewState.Initial)

    fun loadJokes() {
        viewState.value = HomeViewState.Loading
        viewModelScope.launch {
            when (val result = getFiveRandomJokesUseCase.perform()) {
                is SafeResult.Success -> viewState.value = HomeViewState.ShowJokes(result.data)
                is SafeResult.Failure -> {
                    Timber.e("onError")
                    viewState.value = HomeViewState.Error(result.message)
                }
            }
        }
    }
}

sealed class HomeViewState {
    object Initial : HomeViewState()
    object Loading : HomeViewState()
    class ShowJokes(val jokes: List<Joke>) : HomeViewState()
    class Error(val message: String) : HomeViewState()
}