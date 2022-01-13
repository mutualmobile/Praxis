package com.mutualmobile.jokes.home.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.jokes.home.details.JokeDetailsVM.UiState.SuccessState
import com.mutualmobile.praxis.commonui.material.CommonTopAppBar
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTheme

@Composable
fun JokeDetailsScreen(jokeDetailsVM: JokeDetailsVM = hiltViewModel()) {

  Scaffold(
    backgroundColor = PraxisTheme.colors.uiBackground,
    contentColor = PraxisTheme.colors.textSecondary,
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding(),
    topBar = {
      CommonTopAppBar("Joke Detail")
    }) {
    PraxisSurface(
      color = PraxisTheme.colors.uiBackground,
      modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
    ) {
      Column(
        modifier = Modifier
          .fillMaxHeight()
          .fillMaxWidth()
      ) {
        val uiState by jokeDetailsVM.uiState.collectAsState()
        if (uiState is SuccessState) {
          Text(
            text = (uiState as SuccessState).jokeView.joke,
            Modifier.padding(16.dp)
          )
        }
      }
    }
  }
}