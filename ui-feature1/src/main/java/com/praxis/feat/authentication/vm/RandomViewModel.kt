package com.praxis.feat.authentication.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutualmobile.praxis.domain.model.StreamingFile
import com.mutualmobile.praxis.domain.repositories.RandomFileService
import com.mutualmobile.praxis.domain.usecases.FetchRandomPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomViewModel @Inject constructor(
  private val fetchPhotoUseCase: FetchRandomPhotoUseCase,
  private val randomFileService: RandomFileService
) :
  ViewModel() {
  private var fetchJob: Job? = null
  private val viewState = MutableLiveData<RandomPhotoViewState>(
    RandomPhotoViewState.Empty(null)
  )

  init {
    initialState()
  }

  private fun initialState() {
    viewModelScope.launch {
      val file = randomFileService.getTempFile()
      val initialState = RandomPhotoViewState.Empty(
        StreamingFile(file.length(), file, true)
      )
      viewState.value = initialState
    }
  }

  val randomViewState: LiveData<RandomPhotoViewState> = viewState

  private val exceptionHandler: CoroutineExceptionHandler =
    CoroutineExceptionHandler { _, throwable ->
      viewState.value = RandomPhotoViewState.Exception(throwable)
    }

  fun fetchRandomPhoto() {
    fetchJob?.cancel()
    fetchJob = viewModelScope.launch(exceptionHandler) {
      fetchPhotoUseCase.performStreaming(null).handleErrors().collect { streamingFile ->
        viewState.value = RandomPhotoViewState.Streaming(streamingFile)
      }
    }
  }

  sealed class RandomPhotoViewState {
    class Exception(val throwable: Throwable) : RandomPhotoViewState()
    class Streaming(val result: StreamingFile) : RandomPhotoViewState()
    class Empty(val result: StreamingFile?) : RandomPhotoViewState()
  }

  private fun <T> Flow<T>.handleErrors(): Flow<T> = flow {
    try {
      collect { value -> emit(value) }
    } catch (e: Throwable) {
      viewState.value = RandomPhotoViewState.Exception(e)
    }
  }
}