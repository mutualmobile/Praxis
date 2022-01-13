package com.mutualmobile.praxis.data.remote.model

import com.google.gson.annotations.SerializedName
import com.mutualmobile.praxis.data.mapper.EntityMapper
import com.mutualmobile.praxis.data.mapper.DataModel
import com.mutualmobile.praxis.domain.model.Joke
import com.mutualmobile.praxis.domain.model.JokeList

data class JokeListEntity(
  @SerializedName("type")
  val type: String,
  @SerializedName("value")
  val value: List<JokeEntity>
) : DataModel()

data class JokeEntity(
  @SerializedName("id")
  val id: Int,
  @SerializedName("joke")
  val joke: String
) : DataModel()

class JokesListResponseMapper : EntityMapper<JokeList, JokeListEntity> {
  override fun mapToDomain(entity: JokeListEntity): JokeList {
    return JokeList(entity.type, entity.value.map { Joke(it.id, it.joke) })
  }

  override fun mapToEntity(model: JokeList): JokeListEntity {
    return JokeListEntity(model.type, model.Jokes.map { JokeEntity(it.id, it.joke) })
  }

}