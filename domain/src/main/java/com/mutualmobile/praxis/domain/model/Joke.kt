package com.mutualmobile.praxis.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Vipul Asri on 18/01/21.
 */

@Parcelize
data class Joke(
  val id: Int,
  val joke: String
) : Parcelable