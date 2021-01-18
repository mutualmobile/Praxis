package com.mutualmobile.praxis.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Vipul Asri on 18/01/21.
 */

@Parcelize
data class Joke(
  @SerializedName("id")
  val id: Int,
  @SerializedName("joke")
  val joke: String
) : Parcelable