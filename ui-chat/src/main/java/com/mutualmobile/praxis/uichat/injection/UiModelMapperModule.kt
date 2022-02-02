package com.mutualmobile.praxis.uichat.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.mutualmobile.praxis.domain.mappers.UiModelMapper
import com.mutualmobile.praxis.domain.model.channel.PraxisChannel
import com.mutualmobile.praxis.uichat.models.ChannelUIModelMapper
import com.mutualmobile.praxis.uichat.models.ChatPresentation
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UiModelMapperModule {

  @Binds
  @Singleton
  @ChatUiModelMapper
  abstract fun providesUiModelMapper(channelUIModelMapper: ChannelUIModelMapper): UiModelMapper<PraxisChannel, ChatPresentation.PraxisChannel>
}

@Qualifier
annotation class ChatUiModelMapper