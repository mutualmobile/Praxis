package com.mutualmobile.praxis.domain.mappers

open class DomainModel

open class UIModel

interface UiModelMapper<Dom : DomainModel, Ui : UIModel> {
  fun mapToPresentation(model: Dom): Ui

  fun mapToDomain(modelItem: Ui): Dom
}