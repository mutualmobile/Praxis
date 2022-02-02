package com.mutualmobile.feat.jokes.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mutualmobile.feat.jokes.ui.joke.home.JokesScreen
import com.mutualmobile.feat.jokes.ui.joke.jokedetails.JokeDetailsScreen
import com.mutualmobile.praxis.navigator.*

fun NavGraphBuilder.jokesNavGraph() {
  navigation(
    startDestination = PraxisScreen.Jokes.route,
    route = PraxisRoute.Jokes.name
  ) {
    composable(PraxisScreen.Jokes.route) {
      JokesScreen()
    }
    composable(
      PraxisScreen.JokeDetail.route,
      arguments = PraxisScreen.JokeDetail.navArguments
    ) {
      JokeDetailsScreen()
    }
  }
}

