package com.mutualmobile.praxis.data.injection

import com.mutualmobile.praxis.domain.repositories.PhotoFetchRepository
import com.mutualmobile.praxis.domain.usecases.FetchRandomPhotoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RandomPhotosModule {

  @Provides
  @Singleton
  fun provideFetchRandomPhotoUseCase(photoFetchRepository: PhotoFetchRepository) =
    FetchRandomPhotoUseCase(photoFetchRepository)

}
