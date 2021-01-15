package com.mutualmobile.praxis.data.remote.model

import com.google.gson.annotations.SerializedName

data class JokeListResponse(
  @SerializedName("type")
  val type: String,
  @SerializedName("value")
  val value: ArrayList<out Joke>
)