package com.mutualmobile.praxis.ui.joke

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mutualmobile.praxis.domain.model.Joke
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class ShowJokeVM @Inject constructor() : ViewModel() {

  val jokeStringLiveData = MutableLiveData<String>()

  fun showJoke(jokeList: ArrayList<Joke?>?) {
    var jokeString = ""
    jokeList?.let {
      for (joke in jokeList) {
        jokeString = jokeString + joke?.joke?.replace("&quot;", "\"") + "\n\n"
      }
    }
    jokeStringLiveData.postValue(jokeString)
  }

}