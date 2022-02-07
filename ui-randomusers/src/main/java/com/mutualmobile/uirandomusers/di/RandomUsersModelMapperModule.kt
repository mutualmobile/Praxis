package com.mutualmobile.uirandomusers.di

import com.mutualmobile.praxis.domain.mappers.UiModelMapper
import com.mutualmobile.praxis.domain.model.randomuser.DomainLayer.RandomUserResponse
import com.mutualmobile.uirandomusers.model.UiLayer
import com.mutualmobile.uirandomusers.randomusers.RandomUsersModelUiMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RandomUsersModelMapperModule {

  @Binds
  @Singleton
  abstract fun provideRandomUsersModelMapper(randomUsersModelUiMapper: RandomUsersModelUiMapper): UiModelMapper<RandomUserResponse, UiLayer.RandomUserResponse>
}