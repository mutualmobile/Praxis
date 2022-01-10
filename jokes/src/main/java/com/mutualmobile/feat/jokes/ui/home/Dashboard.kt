package com.mutualmobile.feat.jokes.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Dashboard(homeVM: HomeVM = hiltViewModel()) {
  Scaffold(
    backgroundColor = MaterialTheme.colors.background,
    topBar = {
      TopAppBar(title = {
        Text(
          text = "Chuck Norris Random Joke Generator",
          color = MaterialTheme.colors.onPrimary,
          textAlign = TextAlign.Start,
        )
      })
    }) {
    Surface(color = MaterialTheme.colors.background) {
      Column {
        when (homeVM.viewState.value) {
          is HomeViewState.Loading -> {
            LinearProgressIndicator()
          }
          is HomeViewState.ShowJokes -> {
            Text(text = (homeVM.viewState.value as HomeViewState.ShowJokes).jokes,Modifier.padding(16.dp))
          }
          is HomeViewState.Error -> {
            Text(text = "Error")
          }
        }
      }
    }
  }


}