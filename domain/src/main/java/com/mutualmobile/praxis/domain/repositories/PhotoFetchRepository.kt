package com.mutualmobile.praxis.domain.repositories

interface PhotoFetchRepository {
  suspend fun fetchPhoto()

  fun setListener(listener: PhotoFetchListener)
  fun removeListener(listener: PhotoFetchListener)
}