package com.mutualmobile.praxis.ui.model

import android.os.Parcelable
import com.mutualmobile.praxis.domain.model.DOMJoke
import com.mutualmobile.praxis.mapper.UiModelMapper
import com.mutualmobile.praxis.mapper.UIModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UIJoke(val jokeId: Int, val joke: String) : UIModel(),Parcelable

class UIJokeMapper : UiModelMapper<DOMJoke, UIJoke> {
  override fun mapToPresentation(model: DOMJoke): UIJoke {
    return UIJoke(model.id, model.joke)
  }

  override fun mapToDomain(modelItem: UIJoke): DOMJoke {
    return DOMJoke(modelItem.jokeId, modelItem.joke)
  }

}