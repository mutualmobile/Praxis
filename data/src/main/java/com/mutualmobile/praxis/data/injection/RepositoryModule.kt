package com.mutualmobile.praxis.data.injection

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.mutualmobile.praxis.data.repository.PraxisChannelsRepositoryImpl
import com.mutualmobile.praxis.data.repository.PraxisMessagesRepositoryImpl
import com.mutualmobile.praxis.domain.repository.ChannelsRepository
import com.mutualmobile.praxis.domain.repository.MessagesRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Binds
  @Singleton
  abstract fun bindLocalChannelsRepository(slackLocalChannelsRepositoryImpl: PraxisChannelsRepositoryImpl): ChannelsRepository

  @Binds
  @Singleton
  abstract fun bindMessagesRepository(slackMessagesRepositoryImpl: PraxisMessagesRepositoryImpl): MessagesRepository

}