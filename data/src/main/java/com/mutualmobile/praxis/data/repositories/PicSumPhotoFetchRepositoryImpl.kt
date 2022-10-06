package com.mutualmobile.praxis.data.repositories

import com.mutualmobile.praxis.domain.model.StreamingFile
import com.mutualmobile.praxis.domain.repositories.PhotoFetchListener
import com.mutualmobile.praxis.domain.repositories.PhotoFetchRepository
import com.mutualmobile.praxis.domain.repositories.RandomFileService
import com.mutualmobile.praxis.injection.dispatcher.CoroutineDispatcherProvider
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.onDownload
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import java.io.File
import javax.inject.Inject
import kotlinx.coroutines.withContext
import timber.log.Timber

class PicSumPhotoFetchRepositoryImpl @Inject constructor(
  private val coroutineContext: CoroutineDispatcherProvider,
  private val networkClient: HttpClient,
  private val fileCreationService: RandomFileService
) :
  PhotoFetchRepository {
  companion object {
    private const val PIC_SUM_URL = "https://picsum.photos/800"
  }

  private var fileDownloadListener: PhotoFetchListener? = null

  override fun setListener(listener: PhotoFetchListener) {
    fileDownloadListener = listener
  }

  override fun removeListener(listener: PhotoFetchListener) {
    fileDownloadListener = null
  }

  override suspend fun fetchPhoto(): Unit =
    withContext(coroutineContext.io) {
      try {
        val file = fileCreationService.getTempFile()
        val response: HttpResponse = responseWithListener(PIC_SUM_URL, file)
        val bytes = response.body<ByteArray>()
        file.writeBytes(bytes)
        notifyFileDownloaded(file)
      } catch (ex: Exception) {
        fileDownloadListener?.onFailed(ex)
      }
    }

  private fun notifyFileDownloaded(file: File) {
    fileDownloadListener?.onReceive(
      StreamingFile(
        file.length(),
        file, isComplete = true
      )
    )
    fileDownloadListener?.onComplete()
  }

  private suspend fun responseWithListener(
    url: String,
    file: File
  ): HttpResponse = networkClient.get(url) {
    onDownload { bytesSentTotal, contentLength ->
      Timber.d("${bytesSentTotal}/${contentLength}")
      prepareCallback(bytesSentTotal, file)
    }
  }

  private fun prepareCallback(
    bytesSentTotal: Long,
    file: File
  ) {
    fileDownloadListener?.onReceive(
      StreamingFile(
        bytesSentTotal,
        file, isComplete = false
      )
    )
  }
}