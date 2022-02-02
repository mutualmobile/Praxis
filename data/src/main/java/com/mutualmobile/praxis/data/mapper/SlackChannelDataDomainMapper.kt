package com.mutualmobile.praxis.data.mapper

import com.mutualmobile.praxis.data.local.model.DBPraxisChannel
import com.mutualmobile.praxis.domain.model.channel.PraxisOneToOneChannel
import com.mutualmobile.praxis.domain.model.channel.PraxisGroupChannel
import com.mutualmobile.praxis.domain.model.channel.PraxisChannel
import com.mutualmobile.praxis.domain.model.channel.PraxisChannelType
import javax.inject.Inject

class PraxisChannelDataDomainMapper @Inject constructor(): EntityMapper<PraxisChannel, DBPraxisChannel> {
  override fun mapToDomain(entity: DBPraxisChannel): PraxisChannel {
    if (entity.channelType == PraxisChannelType.GROUP) {
      return PraxisGroupChannel(
        description = entity.description,
        createdBy = entity.createdBy,
        isStarred = entity.isStarred,
        isPrivate = entity.isPrivate,
        uuid = entity.uuid,
        name = entity.name,
        createdDate = entity.createdDate,
        modifiedDate = entity.modifiedDate,
        isMuted = entity.isMuted
      )
    } else {
      return PraxisOneToOneChannel(
        uuid = entity.uuid,
        name = entity.name,
        createdDate = entity.createdDate,
        modifiedDate = entity.modifiedDate,
        isMuted = entity.isMuted,
        isPrivate = entity.isPrivate
      )
    }
  }

  override fun mapToData(model: PraxisChannel): DBPraxisChannel {
    TODO("We don't need this yet")
  }
}