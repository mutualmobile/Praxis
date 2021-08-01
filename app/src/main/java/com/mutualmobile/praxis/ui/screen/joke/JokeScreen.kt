package com.mutualmobile.praxis.ui.screen.joke

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.mutualmobile.praxis.domain.model.Joke

/**
 * Created by Rooparsh Kalia on 31/07/21
 */

@Composable
fun JokeScreen(viewModel: ShowJokeVM, jokeList: List<Joke> = emptyList()) {
    viewModel.showJoke(jokeList)
    Scaffold {
        Text(text = viewModel.jokeStringLiveData.value.orEmpty())
    }
}