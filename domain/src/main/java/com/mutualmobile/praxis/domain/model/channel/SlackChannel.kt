package com.mutualmobile.praxis.domain.model.channel

import com.mutualmobile.praxis.domain.mappers.DomainModel

enum class PraxisChannelType {
  ONE_TO_ONE,
  GROUP
}


abstract class PraxisChannel(val channelType: PraxisChannelType) : DomainModel() {
  abstract val uuid: String
  abstract val name: String?
  abstract val createdDate: String
  abstract val modifiedDate: String
  abstract val isMuted: Boolean
  abstract val isPrivate: Boolean

}

data class PraxisOneToOneChannel(
  override val uuid: String,
  override val name: String?,
  override val createdDate: String,
  override val modifiedDate: String,
  override val isMuted: Boolean,
  override val isPrivate: Boolean
) :
  PraxisChannel(PraxisChannelType.ONE_TO_ONE)

data class PraxisGroupChannel(
  val description: String?,
  val createdBy: String,
  val isStarred: Boolean,
  override val uuid: String,
  override val name: String?,
  override val createdDate: String,
  override val modifiedDate: String,
  override val isMuted: Boolean,
  override val isPrivate: Boolean
) : PraxisChannel(PraxisChannelType.GROUP)
