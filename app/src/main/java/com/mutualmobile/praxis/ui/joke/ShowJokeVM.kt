package com.mutualmobile.praxis.ui.joke

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mutualmobile.praxis.ui.model.UIJoke
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class ShowJokeVM @Inject constructor() : ViewModel() {

  val jokeStringLiveData = MutableLiveData<String>()

  fun showJoke(DOMJokeList: ArrayList<UIJoke?>?) {
    var jokeString = ""
    DOMJokeList?.let {
      for (joke in DOMJokeList) {
        jokeString = jokeString + joke?.joke?.replace("&quot;", "\"") + "\n\n"
      }
    }
    jokeStringLiveData.postValue(jokeString)
  }

}