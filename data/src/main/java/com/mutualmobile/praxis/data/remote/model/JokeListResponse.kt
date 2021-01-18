package com.mutualmobile.praxis.data.remote.model

import com.google.gson.annotations.SerializedName

data class JokeListResponse(
  @SerializedName("type")
  val type: String,
  @SerializedName("value")
  val value: ArrayList<out JokeResponse>
) {

  data class JokeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("joke")
    val joke: String
  )

}