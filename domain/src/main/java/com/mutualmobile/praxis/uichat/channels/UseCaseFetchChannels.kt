package com.mutualmobile.praxis.uichat.channels

import com.mutualmobile.praxis.domain.model.channel.PraxisChannel
import com.mutualmobile.praxis.domain.model.channel.PraxisChannelType
import com.mutualmobile.praxis.repository.ChannelsRepository
import com.mutualmobile.praxis.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

class UseCaseFetchChannels(
        private val channelsRepository: ChannelsRepository,
) :
  BaseUseCase<List<PraxisChannel>, PraxisChannelType> {
  override fun performStreaming(params: PraxisChannelType?): Flow<List<PraxisChannel>> {
    return channelsRepository.fetchChannels(params)
  }
}
