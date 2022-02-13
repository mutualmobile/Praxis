package com.mutualmobile.praxis.domain.repositories

import com.mutualmobile.praxis.domain.model.StreamingFile

interface PhotoFetchListener {
  fun onReceive(streamingFile: StreamingFile)
  fun onFailed(throwable: Throwable)
  fun onComplete()
}