package com.mutualmobile.praxis.uichat.models

import com.mutualmobile.praxis.domain.mappers.UIModel

object ChatPresentation {
  data class PraxisChannel(
    val name: String?,
    val isPrivate: Boolean,
    val uuid: String,
    val createdDate: String,
    val modifiedDate: String,
    val isMuted: Boolean,
  ) : UIModel()
}