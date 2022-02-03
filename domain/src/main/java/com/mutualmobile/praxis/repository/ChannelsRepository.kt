package com.mutualmobile.praxis.repository

import com.mutualmobile.praxis.domain.model.channel.PraxisChannel
import com.mutualmobile.praxis.domain.model.channel.PraxisChannelType
import kotlinx.coroutines.flow.Flow

interface ChannelsRepository {
  fun fetchChannels(params: PraxisChannelType?): Flow<List<PraxisChannel>>

}

