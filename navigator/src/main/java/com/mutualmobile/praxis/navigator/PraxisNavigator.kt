package com.mutualmobile.praxis.navigator

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import kotlinx.coroutines.flow.*

class PraxisNavigator : Navigator() {
  // We use a StateFlow here to allow ViewModels to start observing navigation results before the initial composition,
  // and still get the navigation result later
  private val composeNavControllerFlow = MutableStateFlow<NavController?>(null)
  private val fragmentNavControllerFlow = MutableStateFlow<NavController?>(null)

  override suspend fun handleComposeNavigationCommands(navController: NavController) {
    composeNavigationCommands
      .onSubscription { this@PraxisNavigator.composeNavControllerFlow.value = navController }
      .onCompletion { this@PraxisNavigator.composeNavControllerFlow.value = null }
      .collect { navController.handleNavigationCommand(it) }
  }

  override suspend fun handleFragmentNavigationCommands(navController: NavController) {
    fragmentNavigationCommands
      .onSubscription { this@PraxisNavigator.fragmentNavControllerFlow.value = navController }
      .onCompletion { this@PraxisNavigator.fragmentNavControllerFlow.value = null }
      .collect { navController.handleNavigationCommand(it) }
  }

  override fun navigateFragment(id: Int) {
    fragmentNavigationCommands.tryEmit(NavigationCommand.FragmentRoute(id))
  }

  override fun navigate(route: String, optionsBuilder: (NavOptionsBuilder.() -> Unit)?) {
    val options = optionsBuilder?.let { navOptions(it) }
    composeNavigationCommands.tryEmit(NavigationCommand.NavigateToRoute(route, options))
  }

  override fun navigateAndClearBackStack(route: String) {
    composeNavigationCommands.tryEmit(NavigationCommand.NavigateToRoute(route, navOptions {
      popUpTo(0)
    }))
  }

  override fun navigateUp() {
    composeNavigationCommands.tryEmit(NavigationCommand.NavigateUp)
  }

  override fun popUpTo(route: String, inclusive: Boolean) {
    composeNavigationCommands.tryEmit(NavigationCommand.PopUpToRoute(route, inclusive))
  }

  override fun <T> navigateBackWithResult(
    key: String,
    result: T,
    destination: String?
  ) {
    composeNavigationCommands.tryEmit(
      NavigationCommand.NavigateUpWithResult(
        key = key,
        result = result,
        destination = destination
      )
    )
  }

  override fun <T> observeResult(key: String, route: String?): Flow<T> {
    return composeNavControllerFlow
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
      is NavigationCommand.FragmentRoute -> {
        navigate(navigationCommand.id)
      }
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