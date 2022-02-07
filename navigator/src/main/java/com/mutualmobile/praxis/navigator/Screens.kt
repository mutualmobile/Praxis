package com.mutualmobile.praxis.navigator

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class PraxisScreen(
  val route: String,
  val navArguments: List<NamedNavArgument> = emptyList()
) {
  val name: String = route.appendArguments(navArguments)

  // onboarding
  object GettingStarted : PraxisScreen("gettingStarted")
  object SkipTypingScreen : PraxisScreen("SkipTypingUI")
  object EmailAddressInputUI : PraxisScreen("EmailAddressInputUI")
  object WorkspaceInputUI : PraxisScreen("WorkspaceInputUI")

  // dashboard
  object Dashboard : PraxisScreen("Dashboard")

  object Auth : PraxisScreen("auth")
  object ForgotPassword : PraxisScreen("forgotPassword")

  object Jokes : PraxisScreen("jokes")
  object JokeDetail : PraxisScreen(
    route = "jokeDetail",
    navArguments = listOf(navArgument("jokeId") { type = NavType.LongType })
  ) {
    fun createRoute(jokeId: String) =
      route.replace("{${navArguments.first().name}}", jokeId)
  }
  object RandomUsers : PraxisScreen("randomUsersUI")

}

sealed class PraxisRoute(val name: String) {
  object OnBoarding : PraxisRoute("onboardingRoute")
  object Auth : PraxisRoute("authenticationRoute")
  object Dashboard : PraxisRoute("dashboardRoute")
  object Jokes : PraxisRoute("jokesRoute")
  object RandomUsers : PraxisScreen("randomUsersRoute")
}

private fun String.appendArguments(navArguments: List<NamedNavArgument>): String {
  val mandatoryArguments = navArguments.filter { it.argument.defaultValue == null }
    .takeIf { it.isNotEmpty() }
    ?.joinToString(separator = "/", prefix = "/") { "{${it.name}}" }
    .orEmpty()
  val optionalArguments = navArguments.filter { it.argument.defaultValue != null }
    .takeIf { it.isNotEmpty() }
    ?.joinToString(separator = "&", prefix = "?") { "${it.name}={${it.name}}" }
    .orEmpty()
  return "$this$mandatoryArguments$optionalArguments"
}