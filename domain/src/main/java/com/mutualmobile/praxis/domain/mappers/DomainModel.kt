package com.mutualmobile.praxis.domain.mappers

interface UiModelMapper<M, MI> {
  fun mapToPresentation(model: M): MI

  fun mapToDomain(modelItem: MI): M
}