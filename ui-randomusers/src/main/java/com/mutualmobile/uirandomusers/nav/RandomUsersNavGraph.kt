package com.mutualmobile.uirandomusers.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mutualmobile.praxis.navigator.PraxisRoute
import com.mutualmobile.praxis.navigator.PraxisScreen
import com.mutualmobile.uirandomusers.randomusers.RandomUsersScreen

fun NavGraphBuilder.randomUsersNavGraph() {
  navigation(
      startDestination = PraxisScreen.RandomUsers.name,
      route = PraxisRoute.RandomUsers.name
  ) {
    composable(PraxisScreen.RandomUsers.name) {
      RandomUsersScreen()
    }
  }
}