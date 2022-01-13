package com.mutualmobile.praxis.navigator

import androidx.navigation.NavOptions

sealed class NavigationCommand {
  object NavigateUp : NavigationCommand()
  data class NavigateToRoute(val route: String, val options: NavOptions? = null) :
    NavigationCommand()

  data class NavigateUpWithResult<T>(
    val key: String,
    val result: T,
    val destination: String? = null
  ) : NavigationCommand()

  data class PopUpToRoute(val route: String, val inclusive: Boolean) : NavigationCommand()
}