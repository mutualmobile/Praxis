package com.mutualmobile.praxis.navigator

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import kotlinx.coroutines.flow.*

class PraxisNavigator : Navigator() {

  override suspend fun handleNavigationCommands(navController: NavController) {
    navigationCommands
      .onSubscription { this@PraxisNavigator.navControllerFlow.value = navController }
      .onCompletion { this@PraxisNavigator.navControllerFlow.value = null }
      .collect { navController.handleNavigationCommand(it) }
  }

  override fun navigate(route: String, optionsBuilder: (NavOptionsBuilder.() -> Unit)?) {
    val options = optionsBuilder?.let { navOptions(it) }
    navigationCommands.tryEmit(NavigationCommand.NavigateToRoute(route, options))
  }

  override fun navigateAndClearBackStack(route: String) {
    navigationCommands.tryEmit(NavigationCommand.NavigateToRoute(route, navOptions {
      popUpTo(0)
    }))
  }

  override fun navigateUp() {
    navigationCommands.tryEmit(NavigationCommand.NavigateUp)
  }

  override fun popUpTo(route: String, inclusive: Boolean) {
    navigationCommands.tryEmit(NavigationCommand.PopUpToRoute(route, inclusive))
  }

  override fun <T> navigateBackWithResult(
    key: String,
    result: T,
    destination: String?
  ) {
    navigationCommands.tryEmit(
      NavigationCommand.NavigateUpWithResult(
        key = key,
        result = result,
        destination = destination
      )
    )
  }

  override fun <T> observeResult(key: String, route: String?): Flow<T> {
    return navControllerFlow
      .filterNotNull()
      .flatMapLatest { navController ->
        val backStackEntry = route?.let { navController.getBackStackEntry(it) }
          ?: navController.currentBackStackEntry

        backStackEntry?.savedStateHandle?.let { savedStateHandle ->
          savedStateHandle.getLiveData<T?>(key)
            .asFlow()
            .filter { it != null }
            .onEach {
              // Nullify the result to avoid resubmitting it
              savedStateHandle.set(key, null)
            }
        } ?: emptyFlow()
      }
  }

  private fun NavController.handleNavigationCommand(navigationCommand: NavigationCommand) {
    when (navigationCommand) {
      is NavigationCommand.NavigateToRoute -> navigate(
        navigationCommand.route,
        navigationCommand.options
      )
      NavigationCommand.NavigateUp -> navigateUp()
      is NavigationCommand.PopUpToRoute -> popBackStack(
        navigationCommand.route,
        navigationCommand.inclusive
      )
      is NavigationCommand.NavigateUpWithResult<*> -> {
        val backStackEntry =
          navigationCommand.destination?.let { getBackStackEntry(it) }
            ?: previousBackStackEntry
        backStackEntry?.savedStateHandle?.set(
          navigationCommand.key,
          navigationCommand.result
        )

        navigationCommand.destination?.let {
          popBackStack(it, false)
        } ?: run {
          navigateUp()
        }
      }
    }
  }
}