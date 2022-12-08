package com.mutualmobile.praxis.domain.usecases

import com.mutualmobile.praxis.domain.model.StreamingFile
import com.mutualmobile.praxis.domain.repositories.PhotoFetchListener
import com.mutualmobile.praxis.domain.repositories.PhotoFetchRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.cancellation.CancellationException

class FetchRandomPhotoUseCase(private val photoFetchRepository: PhotoFetchRepository) :
  BaseUseCase< StreamingFile,String> {

  override fun performStreaming(input: String?): Flow<StreamingFile> {
    return callbackFlow {
      val listener = photoFetchListener()
      photoFetchRepository.setListener(listener)
      photoFetchRepository.fetchPhoto()
      awaitClose {
        photoFetchRepository.removeListener(listener)
      }
    }
  }

  private fun ProducerScope<StreamingFile>.photoFetchListener() =
    object : PhotoFetchListener {
      override fun onReceive(streamingFile: StreamingFile) {
        trySend(streamingFile).onFailure {
          cancel(CancellationException("Download Error", it))
        }
      }

      override fun onFailed(throwable: Throwable) {
        cancel(CancellationException("Download Error", throwable))
      }

      override fun onComplete(){
        channel.close()
      }
    }
}