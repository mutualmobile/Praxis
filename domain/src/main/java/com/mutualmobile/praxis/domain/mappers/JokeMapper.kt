package com.mutualmobile.praxis.domain.mappers

import com.mutualmobile.praxis.domain.model.Joke
import com.mutualmobile.praxis.domain.responsemodels.JokeListResponse

/**
 * Created by Vipul Asri on 18/01/21.
 */

fun JokeListResponse.toJokes(): List<Joke> {
  return this.value.map { jokeResponse ->
    Joke(
        jokeResponse.id,
        jokeResponse.joke
    )
  }
}