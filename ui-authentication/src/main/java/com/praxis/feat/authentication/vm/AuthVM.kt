package com.praxis.feat.authentication.vm

import android.net.Uri
import androidx.lifecycle.*
import com.mutualmobile.praxis.domain.model.StreamingFile
import com.mutualmobile.praxis.domain.usecases.FetchRandomPhotoUseCase
import com.mutualmobile.praxis.navigator.ComposeNavigator
import com.mutualmobile.praxis.navigator.NavigationKeys
import com.mutualmobile.praxis.navigator.PraxisScreen
import com.praxis.feat.authentication.ui.exceptions.FormValidationFailed
import com.praxis.feat.authentication.ui.model.LoginForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.io.FileNotFoundException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class AuthVM @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val composeNavigator: ComposeNavigator,
  private val fetchPhotoUseCase: FetchRandomPhotoUseCase
) : ViewModel() {

  private var fetchJob: Job? = null

  var credentials = MutableStateFlow(LoginForm())
    private set
  var snackBarState = MutableStateFlow("")
    private set
  var formUiState = MutableStateFlow<UiState>(UiState.Empty)
    private set
  var randomPhotoState = MutableStateFlow<UiState>(UiState.Empty)
    private set

  private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    when (throwable) {
      is FormValidationFailed -> {
        snackBarState.value = throwable.failType.message
      }
      is FileNotFoundException -> {
        snackBarState.value = "File Not found"
      }
      is CancellationException -> {
        when (throwable.cause) {
          is UnknownHostException -> {
            snackBarState.value = "No Network"
          }
          else -> {
            snackBarState.value = "Some error happened"
          }
        }
      }
      else -> {
        snackBarState.value = (throwable.message ?: "")
      }
    }

    formUiState.value = UiState.ErrorState(throwable)
    formUiState.value = UiState.Empty
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
    viewModelScope.launch(exceptionHandler) {
      formUiState.value = UiState.LoadingState
      credentials.value.validate()
      snackBarState.value = ""
      delay(1500)
      formUiState.value = UiState.SuccessState("some-token")
      fetchRandomPhoto()
    }
  }

  fun navigateForgotPassword() {
    composeNavigator.navigate(PraxisScreen.ForgotPassword.route)
  }

  fun logout() {
    viewModelScope.launch(exceptionHandler) {
      formUiState.value = UiState.LoadingState
      randomPhotoState.value = UiState.Empty
      delay(1500)
      snackBarState.value = "Logged out!"
      formUiState.value = UiState.Empty
    }
  }


  fun fetchRandomPhoto() {
    fetchJob?.cancel()
    fetchJob = viewModelScope.launch(exceptionHandler) {
      fetchPhotoUseCase.performStreaming(null).handleErrors().collect { streamingFile ->
        randomPhotoState.value = UiState.Streaming(streamingFile)
      }
    }
  }

  private fun <T> Flow<T>.handleErrors(): Flow<T> = flow {
    try {
      collect { value -> emit(value) }
    } catch (e: Throwable) {
      formUiState.value = UiState.ErrorState(e)
    }
  }

  sealed class UiState {
    object Empty : UiState()
    data class Streaming(val result: StreamingFile) : UiState()
    object LoadingState : UiState()
    data class SuccessState(
      val authToken: String,
    ) : UiState()

    data class ErrorState(val throwable: Throwable) : UiState()
  }
}

fun AuthVM.UiState.uri(): Uri? {
  return if (this is AuthVM.UiState.Streaming && this.result.isComplete) {
    Uri.fromFile(this.result.file)
  } else {
    null
  }
}

fun AuthVM.UiState.streamProgress(): String? {
  return if (this is AuthVM.UiState.Streaming) {
    return "${this.result.progress} bytes downloaded."
  } else {
    null
  }
}