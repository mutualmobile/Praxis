package com.mutualmobile.praxis.domain.model

import android.os.Parcelable
import com.mutualmobile.praxis.domain.mappers.DomainModel
import kotlinx.parcelize.Parcelize

/**
 * Created by Vipul Asri on 18/01/21.
 */

@Parcelize
data class Joke(
    val id: Int,
    val joke: String
) : DomainModel(), Parcelable

data class JokeList(val type: String, val Jokes: List<Joke>) : DomainModel()