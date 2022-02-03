package com.mutualmobile.uirandomusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutualmobile.praxis.domain.mappers.UiModelMapper
import com.mutualmobile.praxis.domain.model.randomuser.DomainLayer
import com.mutualmobile.praxis.domain.usecases.BaseUseCase
import com.mutualmobile.praxis.domain.usecases.UseCaseFetchRandomUsers
import com.mutualmobile.uirandomusers.model.RandomUser
import com.mutualmobile.uirandomusers.model.UiLayer
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RandomUsersVM(
  private val baseUseCase: UseCaseFetchRandomUsers,
  private val mapper: UiModelMapper<DomainLayer.RandomUserResponse, UiLayer.RandomUserResponse>

) : ViewModel() {
  private val _uiState = MutableStateFlow<UiState>(UiState.Empty)

  val uiState: StateFlow<UiState>
    get() = _uiState

  fun fetchRandomUsers() {
    viewModelScope.launch(CoroutineExceptionHandler { context, throwable ->
      _uiState.value = UiState.Error
    }) {
      _uiState.value = UiState.Loading
      val result = baseUseCase.perform(50)
      val resultUi = mapper.mapToPresentation(result)
      _uiState.value = UiState.Data(resultUi)
    }
  }
}

sealed class UiState {
  object Empty : UiState()
  data class Data<T>(var data: T) : UiState()
  object Loading : UiState()
  object Error : UiState()
}
