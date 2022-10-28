package com.mutualmobile.praxis.uionboarding.nav


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mutualmobile.praxis.navigator.AbsComposeNavigator
import com.mutualmobile.praxis.navigator.PraxisRoute
import com.mutualmobile.praxis.navigator.PraxisScreen
import com.mutualmobile.praxis.uionboarding.compose.EmailAddressInputUI
import com.mutualmobile.praxis.uionboarding.compose.GettingStartedUI
import com.mutualmobile.praxis.uionboarding.compose.SkipTypingUI
import com.mutualmobile.praxis.uionboarding.compose.WorkspaceInputUI

fun NavGraphBuilder.onboardingNavigation(
  absComposeNavigator: AbsComposeNavigator,
) {
  navigation(
    startDestination = PraxisScreen.GettingStarted.name,
    route = PraxisRoute.OnBoarding.name
  ) {
    composable(PraxisScreen.GettingStarted.name) {
      GettingStartedUI(absComposeNavigator)
    }
    composable(PraxisScreen.SkipTypingScreen.name) {
      SkipTypingUI(absComposeNavigator)
    }
    composable(PraxisScreen.WorkspaceInputUI.name) {
      WorkspaceInputUI(absComposeNavigator)
    }
    composable(PraxisScreen.EmailAddressInputUI.name) {
      EmailAddressInputUI(absComposeNavigator)
    }
  }

}