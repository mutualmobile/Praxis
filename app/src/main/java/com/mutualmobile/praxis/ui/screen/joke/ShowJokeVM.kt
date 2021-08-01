package com.mutualmobile.praxis.ui.screen.joke

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mutualmobile.praxis.domain.model.Joke
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowJokeVM @Inject constructor() : ViewModel() {

    val jokes = mutableStateOf("")

    fun showJoke(jokeList: List<Joke>) {
        var jokeString = ""
        for (joke in jokeList) {
            jokeString = jokeString + joke.joke.replace("&quot;", "\"") + "\n\n"
        }

        jokes.value = jokeString
    }

}