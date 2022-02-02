package com.mutualmobile.praxis.uichat.models

import com.mutualmobile.praxis.domain.mappers.DomainModel
import com.mutualmobile.praxis.domain.mappers.UiModelMapper
import com.mutualmobile.praxis.domain.model.channel.PraxisChannel
import javax.inject.Inject

class ChannelUIModelMapper @Inject constructor() :
  UiModelMapper<PraxisChannel, ChatPresentation.PraxisChannel> {
  override fun mapToPresentation(model: PraxisChannel): ChatPresentation.PraxisChannel {
    return ChatPresentation.PraxisChannel(
      model.name,
      model.isPrivate,
      model.uuid,
      model.createdDate,
      model.modifiedDate,
      model.isMuted
    )
  }

  override fun mapToDomain(modelItem: ChatPresentation.PraxisChannel): PraxisChannel {
    TODO("Not yet implemented")
  }
}