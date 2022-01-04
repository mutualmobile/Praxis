package com.mutualmobile.praxis.navigator

import com.mutualmobile.praxis.navigator.directions.AuthenticationDirections
import com.mutualmobile.praxis.navigator.directions.NavigationCommand
import kotlinx.coroutines.flow.MutableStateFlow

class Navigator {
  var commands = MutableStateFlow(AuthenticationDirections.Default)

  fun navigate(
    directions: NavigationCommand
  ) {
    commands.value = directions
  }

}

