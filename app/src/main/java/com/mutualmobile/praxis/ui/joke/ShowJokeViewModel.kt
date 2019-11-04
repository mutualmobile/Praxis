package com.mutualmobile.praxis.ui.joke

import androidx.lifecycle.MutableLiveData
import com.mutualmobile.praxis.data.model.Joke
import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.mutualmobile.praxis.ui.base.BaseViewModel
import javax.inject.Inject

@ActivityScope
class ShowJokeViewModel @Inject constructor() : BaseViewModel() {

  val jokeStringLiveData = MutableLiveData<String>()

  fun showJoke(jokeList: ArrayList<Joke>) {
    var jokeString = ""
    for (joke in jokeList) {
      jokeString = jokeString + joke.joke.replace("&quot;", "\"") + "\n\n"
    }

    jokeStringLiveData.postValue(jokeString)
  }

}