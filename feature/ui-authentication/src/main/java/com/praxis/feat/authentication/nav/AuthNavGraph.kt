package com.praxis.feat.authentication.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mutualmobile.praxis.navigator.PraxisRoute
import com.mutualmobile.praxis.navigator.PraxisScreen
import com.praxis.feat.authentication.ui.AuthenticationUI
import com.praxis.feat.authentication.ui.ForgotPasswordUI

fun NavGraphBuilder.authNavGraph() {
  navigation(
    startDestination = PraxisScreen.Auth.route,
    route = PraxisRoute.Auth.name
  ) {
    composable(PraxisScreen.Auth.route) {
      AuthenticationUI()
    }
    composable(PraxisScreen.ForgotPassword.route) {
      ForgotPasswordUI()
    }
  }
}

