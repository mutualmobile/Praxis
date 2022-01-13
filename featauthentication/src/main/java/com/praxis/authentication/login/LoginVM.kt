package com.praxis.authentication.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutualmobile.praxis.navigator.NavigationKeys
import com.mutualmobile.praxis.navigator.Navigator
import com.mutualmobile.praxis.navigator.Screen
import com.praxis.authentication.common.model.LoginForm
import com.praxis.authentication.login.LoginVM.UiState.Empty
import com.praxis.authentication.login.LoginVM.UiState.Error
import com.praxis.authentication.login.LoginVM.UiState.SuccessWithData
import com.praxis.authentication.login.exceptions.FormValidationException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val navigator: Navigator
) : ViewModel() {

  var credentials = MutableStateFlow(LoginForm())
    private set
  var snackBarState = MutableStateFlow("")
    private set

  var uiState = MutableStateFlow<UiState>(Empty)

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

  fun login() {
    try {
      val isValid = credentials.value.validate()
      if (isValid) {
        snackBarState.value = ""
        navigator.navigate(Screen.Jokes.route)
      }
      uiState.value = SuccessWithData("some_jwt")
    } catch (ex: FormValidationException) {
      uiState.value = Error(throwable = ex)
      snackBarState.value = ex.failType.message
    }
  }

  fun navigateForgotPassword() {
    navigator.navigate(Screen.ForgotPassword.route)
  }

  sealed class UiState {
    object Empty : UiState()
    object Loading : UiState()
    data class SuccessWithData(
      val authToken: String,
    ) : UiState()

    data class Error(val throwable: Throwable) : UiState()
  }
}