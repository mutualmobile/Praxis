package com.mutualmobile.praxis.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NavOptions

@Immutable
sealed class NavigationCommand {
  object NavigateUp : NavigationCommand()
}

@Immutable
sealed class ComposeNavigationCommand : NavigationCommand() {
  @Immutable data class NavigateToRoute(val route: String, val options: NavOptions? = null) :
    ComposeNavigationCommand()

  @Immutable data class NavigateUpWithResult<T>(
    val key: String,
    val result: T,
    val route: String? = null
  ) : ComposeNavigationCommand()

  @Immutable data class PopUpToRoute(val route: String, val inclusive: Boolean) : ComposeNavigationCommand()
}