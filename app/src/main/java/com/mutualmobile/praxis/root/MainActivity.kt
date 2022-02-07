package com.mutualmobile.praxis.root

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.mutualmobile.feat.jokes.nav.jokesNavGraph
import dagger.hilt.android.AndroidEntryPoint
import com.mutualmobile.praxis.navigator.ComposeNavigator
import com.mutualmobile.praxis.navigator.PraxisRoute
import com.mutualmobile.praxis.uidashboard.nav.dashboardNavigation
import com.mutualmobile.praxis.uionboarding.nav.onboardingNavigation
import com.mutualmobile.uirandomusers.nav.randomUsersNavGraph
import com.praxis.feat.authentication.nav.authNavGraph
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var composeNavigator: ComposeNavigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)

    installSplashScreen()
    setContent {
      val navController = rememberNavController()

      LaunchedEffect(Unit) {
        composeNavigator.handleNavigationCommands(navController)
      }

      ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        NavHost(
          navController = navController,
          startDestination = PraxisRoute.RandomUsers.name,
        ) {
          onboardingNavigation(
            composeNavigator = composeNavigator,
          )
          dashboardNavigation(
            composeNavigator = composeNavigator
          )
          authNavGraph()
          jokesNavGraph()
          randomUsersNavGraph()
        }
      }


    }
  }
}