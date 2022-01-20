package com.mutualmobile.praxis.navigator

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class Navigator {

  val composeNavigationCommands =
    MutableSharedFlow<NavigationCommand>(extraBufferCapacity = Int.MAX_VALUE)

  val fragmentNavigationCommands =
    MutableSharedFlow<NavigationCommand>(extraBufferCapacity = Int.MAX_VALUE)


  abstract suspend fun handleComposeNavigationCommands(navController: NavController)
  abstract suspend fun handleFragmentNavigationCommands(navController: NavController)
  abstract fun navigate(route: String, optionsBuilder: (NavOptionsBuilder.() -> Unit)? = null)
  abstract fun navigateFragment(id: Int)
  abstract fun navigateAndClearBackStack(route: String)
  abstract fun navigateUp()
  abstract fun popUpTo(route: String, inclusive: Boolean)
  abstract fun <T> navigateBackWithResult(key: String, result: T, destination: String?)
  abstract fun <T> observeResult(key: String, route: String? = null): Flow<T>
}


fun <T> LiveData<T>.asFlow(): Flow<T> = flow {
  val channel = Channel<T>(Channel.CONFLATED)
  val observer = Observer<T> {
    channel.trySend(it)
  }
  withContext(Dispatchers.Main.immediate) {
    observeForever(observer)
  }
  try {
    for (value in channel) {
      emit(value)
    }
  } finally {
    GlobalScope.launch(Dispatchers.Main.immediate) {
      removeObserver(observer)
    }
  }
}