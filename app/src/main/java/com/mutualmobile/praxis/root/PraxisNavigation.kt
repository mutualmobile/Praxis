package com.mutualmobile.praxis.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.accompanist.insets.ProvideWindowInsets
import com.mutualmobile.feat.jokes.ui.home.Dashboard
import com.mutualmobile.feat.jokes.ui.home.JokeDetailsScreen
import com.mutualmobile.praxis.commonui.theme.AlphaNearTransparent
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.mutualmobile.praxis.navigator.Navigator
import com.mutualmobile.praxis.navigator.directions.AuthenticationDirections
import com.mutualmobile.praxis.navigator.directions.DashboardDirections
import com.praxis.feat.authentication.AuthenticationUI
import com.praxis.feat.authentication.ForgotPasswordUI

@Composable
fun PraxisNavigation(navigator:Navigator) {
  ProvideWindowInsets {
    PraxisSurface(
      color = PraxisTheme.colors.statusBarColor.copy(alpha = AlphaNearTransparent),
      modifier = Modifier.fillMaxSize()
    ) {
      val navController = rememberNavController()
      navigator.commands.collectAsState().value.also { command ->
        if (command.destination.isNotEmpty()) {
          navController.navigate(command.destination)
        }
      }
      NavHost(
        navController = navController,
        startDestination = AuthenticationDirections.root.destination
      ) {

        navigation(
          startDestination = AuthenticationDirections.authentication.destination,
          route = AuthenticationDirections.root.destination
        ) {
          composable(AuthenticationDirections.authentication.destination) {
            AuthenticationUI()
          }
          composable(AuthenticationDirections.forgotPassword.destination) {
            ForgotPasswordUI()
          }
        }

        navigation(
          startDestination = AuthenticationDirections.dashboard.destination,
          route = DashboardDirections.root.destination
        ) {
          composable(AuthenticationDirections.dashboard.destination) {
            Dashboard()
          }
          composable(DashboardDirections.jokeDetails.destination) {
            JokeDetailsScreen()
          }
        }
      }
    }
  }

}
