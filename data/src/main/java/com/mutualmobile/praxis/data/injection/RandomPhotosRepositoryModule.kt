package com.mutualmobile.praxis.data.injection

import com.mutualmobile.praxis.data.repositories.PicSumPhotoFetchRepositoryImpl
import com.mutualmobile.praxis.data.repositories.RandomFileServiceImpl
import com.mutualmobile.praxis.domain.repositories.PhotoFetchRepository
import com.mutualmobile.praxis.domain.repositories.RandomFileService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RandomPhotosRepositoryModule {

  @Binds
  abstract fun bindRandomFileService(randomFileServiceImpl: RandomFileServiceImpl): RandomFileService

  @Binds
  abstract fun bindPhotoFetchRepository(photoFetchRepositoryImpl: PicSumPhotoFetchRepositoryImpl): PhotoFetchRepository
}