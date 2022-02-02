package com.mutualmobile.praxis.uidashboard.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mutualmobile.praxis.navigator.ComposeNavigator
import com.mutualmobile.praxis.navigator.PraxisRoute
import com.mutualmobile.praxis.navigator.PraxisScreen
import com.mutualmobile.praxis.uidashboard.compose.DashboardUI

fun NavGraphBuilder.dashboardNavigation(
  composeNavigator: ComposeNavigator,
) {
  navigation(
    startDestination = PraxisScreen.Dashboard.name,
    route = PraxisRoute.Dashboard.name
  ) {
    composable(PraxisScreen.Dashboard.name) {
      DashboardUI(composeNavigator)
    }
  }
}