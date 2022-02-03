package com.mutualmobile.praxis.uichat.chat

import androidx.paging.PagingData
import com.mutualmobile.praxis.domain.model.channel.PraxisChannel
import com.mutualmobile.praxis.domain.model.message.PraxisMessage
import com.mutualmobile.praxis.repository.MessagesRepository
import com.mutualmobile.praxis.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

class UseCaseFetchMessages(private val messagesRepository: MessagesRepository) : BaseUseCase<PagingData<PraxisMessage>, PraxisChannel> {
  override fun performStreaming(params: PraxisChannel?): Flow<PagingData<PraxisMessage>> {
    return messagesRepository.fetchMessages(params)
  }
}
