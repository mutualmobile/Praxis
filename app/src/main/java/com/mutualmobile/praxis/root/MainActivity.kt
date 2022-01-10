package com.mutualmobile.praxis.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.mutualmobile.feat.jokes.ui.home.Dashboard
import com.mutualmobile.feat.jokes.ui.home.JokeDetailsScreen
import com.mutualmobile.praxis.navigator.Navigator
import com.mutualmobile.praxis.navigator.directions.AuthenticationDirections
import com.mutualmobile.praxis.navigator.directions.DashboardDirections
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.praxis.feat.authentication.AuthenticationUI
import com.praxis.feat.authentication.ForgotPasswordUI
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var navigator: Navigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PraxisTheme {
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
}