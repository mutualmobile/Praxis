package com.mutualmobile.praxis.data.mapper

import com.mutualmobile.praxis.data.local.model.DBPraxisMessage
import com.mutualmobile.praxis.domain.model.message.PraxisMessage
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PraxisMessageDataDomMapper @Inject constructor() : EntityMapper<PraxisMessage, DBPraxisMessage> {
  override fun mapToDomain(entity: DBPraxisMessage): PraxisMessage {
    return PraxisMessage(
      entity.uuid,
      entity.message,
      entity.userId,
      entity.createdBy,
      entity.createdDate,
      entity.modifiedDate
    )
  }

  override fun mapToData(model: PraxisMessage): DBPraxisMessage {
    return DBPraxisMessage(
      model.uuid,
      model.message,
      model.userId,
      model.createdBy,
      model.createdDate,
      model.modifiedDate,
    )
  }
}