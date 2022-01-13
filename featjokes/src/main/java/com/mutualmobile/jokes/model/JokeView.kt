package com.mutualmobile.jokes.model

import android.os.Parcelable
import com.mutualmobile.praxis.domain.mappers.UIModel
import com.mutualmobile.praxis.domain.model.Joke
import com.mutualmobile.praxis.domain.mappers.UiModelMapper
import kotlinx.parcelize.Parcelize

@Parcelize
data class JokeView(val jokeId: Int, val joke: String) : UIModel(), Parcelable

class JokeViewMapper : UiModelMapper<Joke, JokeView> {
  override fun mapToPresentation(model: Joke): JokeView {
    return JokeView(model.id, model.joke)
  }

  override fun mapToDomain(modelView: JokeView): Joke {
    return Joke(modelView.jokeId, modelView.joke)
  }

}