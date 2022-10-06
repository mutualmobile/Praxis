package com.mutualmobile.praxis.data.mapper

interface EntityMapper<M, ME> {
  fun mapToDomain(entity: ME): M

  fun mapToData(model: M): ME
}
