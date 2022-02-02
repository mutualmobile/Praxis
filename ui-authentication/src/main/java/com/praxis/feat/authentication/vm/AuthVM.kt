package com.praxis.feat.authentication.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutualmobile.praxis.navigator.ComposeNavigator
import com.mutualmobile.praxis.navigator.FragmentNavGraphNavigator
import com.mutualmobile.praxis.navigator.NavigationKeys
import com.mutualmobile.praxis.navigator.Screen
import com.praxis.feat.authentication.R
import com.praxis.feat.authentication.ui.exceptions.FormValidationFailed
import com.praxis.feat.authentication.ui.model.LoginForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthVM @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val fragmentNavGraphNavigator: FragmentNavGraphNavigator,
  private val composeNavigator: ComposeNavigator
) : ViewModel() {

  var credentials = MutableStateFlow(LoginForm())
    private set
  var snackBarState = MutableStateFlow("")
    private set
  var uiState = MutableStateFlow<UiState>(UiState.Empty)
    private set

  private val exceptionHandler = CoroutineExceptionHandler { context, throwable ->
    if (throwable is FormValidationFailed) {
      snackBarState.value = throwable.failType.message
    }
    uiState.value = UiState.ErrorState(throwable)
  }

  init {
    observePasswordReset()
  }

  private fun observePasswordReset() {
    composeNavigator.observeResult<String>(NavigationKeys.ForgotPassword).onStart {
      val message = savedStateHandle.get<String>(NavigationKeys.ForgotPassword)
      message?.let {
        emit(it)
      }
    }
      .onEach { snackBarState.value = it }
      .launchIn(viewModelScope)
  }

  fun loginNow() {
    uiState.value = UiState.LoadingState

    viewModelScope.launch(exceptionHandler) {
      credentials.value.validate()
      snackBarState.value = ""
      uiState.value = UiState.SuccessState("sdff")
      fragmentNavGraphNavigator.navigateFragment(R.id.action_authFragment_to_viewPagerFragment)
    }
  }

  fun navigateForgotPassword() {
    composeNavigator.navigate(Screen.ForgotPassword.route)
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