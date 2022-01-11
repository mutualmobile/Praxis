package com.praxis.feat.authentication.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptionsBuilder
import com.mutualmobile.praxis.navigator.NavigationKeys
import com.mutualmobile.praxis.navigator.Navigator
import com.mutualmobile.praxis.navigator.Screen
import com.praxis.feat.authentication.ui.exceptions.FormValidationFailed
import com.praxis.feat.authentication.ui.model.LoginForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AuthVM @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val navigator: Navigator
) : ViewModel() {

  var credentials = MutableStateFlow(LoginForm())
    private set
  var snackBarState = MutableStateFlow("")
    private set

  var uiState = MutableStateFlow<UiState>(UiState.Empty)

  init {
    observePasswordReset()
  }

  private fun observePasswordReset() {
    navigator.observeResult<String>(NavigationKeys.ForgotPassword).onStart {
      val message = savedStateHandle.get<String>(NavigationKeys.ForgotPassword)
      message?.let {
        emit(it)
      }
    }
      .onEach { snackBarState.value = it }
      .launchIn(viewModelScope)
  }

  fun loginNow() {
    try {
      val isValid = credentials.value.validate()
      if (isValid) {
        snackBarState.value = ""
        navigator.navigate(Screen.Jokes.route)
      }
      uiState.value = UiState.SuccessState("some_jwt")
    } catch (ex: FormValidationFailed) {
      uiState.value = UiState.ErrorState(throwable = ex)
      snackBarState.value = ex.failType.message
    }
  }

  fun navigateForgotPassword() {
    navigator.navigate(Screen.ForgotPassword.route)
  }

  sealed class UiState {
    object Empty : UiState()
    object LoadingState : UiState()
    data class SuccessState(
      val authToken: String,
    ) : UiState()

    data class ErrorState(val throwable: Throwable) : UiState()
  }
}