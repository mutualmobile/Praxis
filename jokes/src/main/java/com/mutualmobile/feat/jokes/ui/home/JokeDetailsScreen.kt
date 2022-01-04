package com.mutualmobile.feat.jokes.ui.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun JokeDetailsScreen(jokeDetailVM: JokeDetailVM = hiltViewModel()) {

  Surface(color = MaterialTheme.colors.background) {
    Text(text = "joke details")
  }
}