package com.mutualmobile.praxis.data.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.mutualmobile.praxis.data.repository.PraxisChannelsRepositoryImpl
import com.mutualmobile.praxis.data.repository.PraxisMessagesRepositoryImpl
import com.mutualmobile.praxis.repository.ChannelsRepository
import com.mutualmobile.praxis.repository.MessagesRepository
import com.mutualmobile.praxis.repository.RandomUserRepository
import com.mutualmobile.praxis.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Binds
  @Singleton
  abstract fun bindLocalChannelsRepository(slackLocalChannelsRepositoryImpl: PraxisChannelsRepositoryImpl): ChannelsRepository

  @Binds
  @Singleton
  abstract fun bindMessagesRepository(slackMessagesRepositoryImpl: PraxisMessagesRepositoryImpl): MessagesRepository

  @Binds
  @Singleton
  abstract fun bindUserRepository(randomUserRepository: RandomUserRepository): UserRepository
}
