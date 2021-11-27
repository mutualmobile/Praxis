package com.mutualmobile.praxis.mapper

import com.mutualmobile.praxis.domain.mappers.DomainModel

open class UIModel

interface UiModelMapper<M : DomainModel, MI : UIModel> {
    fun mapToPresentation(model: M): UIModel

    fun mapToDomain(modelItem: MI): DomainModel
}