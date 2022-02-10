package com.mutualmobile.uirandomusers.randomusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutualmobile.praxis.domain.mappers.UiModelMapper
import com.mutualmobile.praxis.domain.model.randomuser.DomainLayer
import com.mutualmobile.praxis.domain.usecases.UseCaseFetchRandomUsers
import com.mutualmobile.uirandomusers.model.UiLayer
import com.mutualmobile.uirandomusers.model.UiLayer.RandomUserResponse
import com.mutualmobile.uirandomusers.randomusers.RandomUsersVM.UiState.Data
import com.mutualmobile.uirandomusers.randomusers.RandomUsersVM.UiState.Empty
import com.mutualmobile.uirandomusers.randomusers.RandomUsersVM.UiState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomUsersVM @Inject constructor(
  private val baseUseCase: UseCaseFetchRandomUsers,
  private val mapper: UiModelMapper<DomainLayer.RandomUserResponse, UiLayer.RandomUserResponse>

) : ViewModel() {
  private val _uiState = MutableStateFlow<UiState>(Empty)

  val uiState: StateFlow<UiState>
    get() = _uiState

  init {
    fetchRandomUsers()
  }

  //accompanist insets
  fun fetchRandomUsers() {
    viewModelScope.launch(CoroutineExceptionHandler { context, throwable ->
      _uiState.value = UiState.Error
    }) {
      _uiState.value = Loading
      val result = baseUseCase.perform(50)
      val resultUi = mapper.mapToPresentation(result)
      _uiState.value = Data(resultUi)
    }
  }

  sealed class UiState {
    object Empty : UiState()
    data class Data(var data: RandomUserResponse) : UiState()
    object Loading : UiState()
    object Error : UiState()
  }
}


