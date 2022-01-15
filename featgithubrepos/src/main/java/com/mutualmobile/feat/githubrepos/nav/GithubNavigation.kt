package com.mutualmobile.feat.githubrepos.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.mutualmobile.praxis.commonui.theme.AlphaNearOpaque
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.mutualmobile.praxis.navigator.Navigator
import com.mutualmobile.praxis.navigator.Screen

@Composable
fun GithubNavigation(navigator: Navigator) {
  ProvideWindowInsets {
    PraxisSurface(
      color = PraxisTheme.colors.statusBarColor.copy(alpha = AlphaNearOpaque),
      modifier = Modifier.fillMaxSize()
    ) {
      val navController = rememberNavController()

      LaunchedEffect(Unit) {
        navigator.handleNavigationCommands(navController)
      }

      NavHost(
        navController = navController,
        startDestination = Screen.RepoDetails.route
      ) {
        composable(Screen.RepoDetails.route) {
          //AuthenticationUI()
        }
      }
    }
  }
}

