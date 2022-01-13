package com.mutualmobile.praxis.domain.mappers

open class DomainModel

open class UIModel

interface UiModelMapper<M : DomainModel, MV : UIModel> {
  fun mapToPresentation(model: M): UIModel

  fun mapToDomain(modelView: MV): DomainModel
}