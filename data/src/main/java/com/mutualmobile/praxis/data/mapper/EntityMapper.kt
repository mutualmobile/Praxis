package com.mutualmobile.praxis.data.mapper

import com.mutualmobile.praxis.domain.mappers.DomainModel

interface EntityMapper<M : DomainModel, ME : DataModel> {
  fun mapToDomain(entity: ME): M

  fun mapToData(model: M): ME
}

open class DataModel
