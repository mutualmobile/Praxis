package com.mutualmobile.praxis.domain.model.message

import com.mutualmobile.praxis.domain.mappers.DomainModel

data class PraxisMessage(
  val uuid: String,
  val message: String,
  val userId: String,
  val createdBy: String,
  val createdDate: Long,
  val modifiedDate: Long,
) : DomainModel()