package com.mutualmobile.jokes.home.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mutualmobile.praxis.commonui.material.CommonTopAppBar
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.jokes.home.dashboard.HomeViewState.Error
import com.mutualmobile.jokes.home.dashboard.HomeViewState.Loading
import com.mutualmobile.jokes.home.dashboard.HomeViewState.ShowJokes
import com.mutualmobile.jokes.model.JokeView
import com.mutualmobile.praxis.commonui.theme.PraxisTypography

@Composable
fun Dashboard(homeVM: HomeVM = hiltViewModel()) {
  Scaffold(
    backgroundColor = PraxisTheme.colors.uiBackground,
    contentColor = PraxisTheme.colors.textSecondary,
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding(),
    topBar = {
      CommonTopAppBar("Chuck Norris Random Joke Generator")
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
        val state by homeVM.viewState.collectAsState()
        when (state) {
          is Loading -> {
            LinearProgressIndicator(
              color = PraxisTheme.colors.accent, modifier = Modifier
                .fillMaxWidth()
            )
          }
          is ShowJokes -> {
            val jokes = (state as ShowJokes).jokeViews
            JokesList(jokes, homeVM)

          }
          is Error -> {
            Text(text = "Error")
          }
        }
      }
    }
  }


}

@Composable
private fun JokesList(
  jokeViews: List<JokeView>,
  homeVM: HomeVM
) {
  LazyColumn {
    items(jokeViews.size) { item ->
      ClickableText(text = buildAnnotatedString {
        withStyle(
          style = SpanStyle(
            color = PraxisTheme.colors.textPrimary,
            fontSize = PraxisTypography.body1.fontSize
          )
        ) {
          append(jokeViews[item].joke)
        }

      }, onClick = {
        homeVM.showJoke(jokeViews[item].jokeId)
      }, modifier = Modifier.padding(16.dp))
    }
  }
}