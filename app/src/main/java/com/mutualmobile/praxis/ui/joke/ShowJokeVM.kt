package com.mutualmobile.praxis.ui.joke

import androidx.lifecycle.MutableLiveData
import com.mutualmobile.praxis.data.remote.model.Joke
import com.mutualmobile.praxis.ui.base.BaseVM
import javax.inject.Inject

class ShowJokeVM @Inject constructor() : BaseVM() {

  val jokeStringLiveData = MutableLiveData<String>()

  fun showJoke(jokeList: ArrayList<Joke>) {
    var jokeString = ""
    for (joke in jokeList) {
      jokeString = jokeString + joke.joke.replace("&quot;", "\"") + "\n\n"
    }

    jokeStringLiveData.postValue(jokeString)
  }

}