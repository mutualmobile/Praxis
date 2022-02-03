package com.mutualmobile.praxis.uichat

import com.mutualmobile.praxis.domain.model.message.PraxisMessage
import com.mutualmobile.praxis.repository.MessagesRepository
import com.mutualmobile.praxis.domain.usecases.BaseUseCase

class UseCaseSendMessage(private val messagesRepository: MessagesRepository) :BaseUseCase<Unit,PraxisMessage>{
  override suspend fun perform(params: PraxisMessage) {
    return messagesRepository.sendMessage(params)
  }
}
