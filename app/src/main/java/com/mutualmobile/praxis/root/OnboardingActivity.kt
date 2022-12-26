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
import com.mutualmobile.praxis.navigator.ComposeNavigator
import com.mutualmobile.praxis.navigator.PraxisRoute
import com.mutualmobile.praxis.uionboarding.nav.onboardingNavigation
import com.praxis.feat.authentication.nav.authNavGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.mutualmobile.praxis.BuildConfig

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

  @Inject
  lateinit var composeNavigator: ComposeNavigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)

    AppCenter.start(
      application, BuildConfig.APP_CENTER_SECRET_DEVELOPMENT,
      Analytics::class.java, Crashes::class.java
    )

    installSplashScreen()
    setContent {
      val navController = rememberNavController()

      LaunchedEffect(Unit) {
        composeNavigator.handleNavigationCommands(navController)
      }

      ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        NavHost(
          navController = navController,
          startDestination = PraxisRoute.OnBoarding.name,
        ) {
          onboardingNavigation(
            composeNavigator = composeNavigator,
          )
          authNavGraph()
        }
      }


    }
  }
}