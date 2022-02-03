package com.mutualmobile.praxis.uionboarding.nav


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mutualmobile.praxis.navigator.ComposeNavigator
import com.mutualmobile.praxis.navigator.PraxisRoute
import com.mutualmobile.praxis.navigator.PraxisScreen
import com.mutualmobile.praxis.uionboarding.compose.EmailAddressInputUI
import com.mutualmobile.praxis.uionboarding.compose.GettingStartedUI
import com.mutualmobile.praxis.uionboarding.compose.SkipTypingUI
import com.mutualmobile.praxis.uionboarding.compose.WorkspaceInputUI

fun NavGraphBuilder.onboardingNavigation(
  composeNavigator: ComposeNavigator,
) {
  navigation(
    startDestination = PraxisScreen.GettingStarted.name,
    route = PraxisRoute.OnBoarding.name
  ) {
    composable(PraxisScreen.GettingStarted.name) {
      GettingStartedUI(composeNavigator)
    }
    composable(PraxisScreen.SkipTypingScreen.name) {
      SkipTypingUI(composeNavigator)
    }
    composable(PraxisScreen.WorkspaceInputUI.name) {
      WorkspaceInputUI(composeNavigator)
    }
    composable(PraxisScreen.EmailAddressInputUI.name) {
      EmailAddressInputUI(composeNavigator)
    }
  }

}