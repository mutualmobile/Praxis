package com.mutualmobile.praxis.domain.repository

import androidx.paging.PagingData
import com.mutualmobile.praxis.domain.model.channel.PraxisChannel
import com.mutualmobile.praxis.domain.model.message.PraxisMessage
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {
  fun fetchMessages(params: PraxisChannel?): Flow<PagingData<PraxisMessage>>
  suspend fun sendMessage(params: PraxisMessage)
}